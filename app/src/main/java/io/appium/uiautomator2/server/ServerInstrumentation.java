/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.server;

import android.app.UiAutomation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.Configurator;

import io.appium.uiautomator2.common.exceptions.SessionRemovedException;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.ShutdownOnPowerDisconnect;
import io.appium.uiautomator2.server.mjpeg.MjpegScreenshotServer;
import io.appium.uiautomator2.utils.Logger;

import static android.content.Intent.ACTION_POWER_DISCONNECTED;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static io.appium.uiautomator2.server.ServerConfig.getMjpegServerPort;
import static io.appium.uiautomator2.server.ServerConfig.getServerPort;
import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class ServerInstrumentation {
    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 65535;
    private static final String WAKE_LOCK_TAG = "UiAutomator2:ScreenKeeper";
    private static final long MAX_TEST_DURATION = 24 * 60 * 60 * 1000;

    private static ServerInstrumentation instance;

    private final PowerManager powerManager;
    private final int serverPort;
    private final int mjpegServerPort;
    private HttpdThread serverThread;
    private MjpegScreenshotServer mjpegScreenshotServerThread;
    private PowerManager.WakeLock wakeLock;
    private long wakeLockAcquireTimestampMs = 0;
    private long wakeLockTimeoutMs = 0;
    private boolean isServerStopped;

    private void setAccessibilityServiceState() {
        String disableSuppressAccessibilityService = InstrumentationRegistry.getArguments().getString("DISABLE_SUPPRESS_ACCESSIBILITY_SERVICES");
        if (disableSuppressAccessibilityService == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return;
        }

        boolean shouldDisableSuppressAccessibilityService = Boolean.parseBoolean(disableSuppressAccessibilityService);
        if (shouldDisableSuppressAccessibilityService) {
            Configurator.getInstance().setUiAutomationFlags(
                    UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES);
        } else {
            // We can disable UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES
            // only when we set the value as zero
            Configurator.getInstance().setUiAutomationFlags(0);
        }
    }

    private ServerInstrumentation(Context context, int serverPort, int mjpegServerPort) {
        if (isValidPort(serverPort)) {
            this.serverPort = serverPort;
        } else {
            Logger.warn(String.format(
                "The server port is out of valid range [%s;%s]: %s -- using default: %s",
                MIN_PORT,
                MAX_PORT,
                serverPort,
                ServerConfig.DEFAULT_SERVER_PORT
            ));
            this.serverPort = ServerConfig.DEFAULT_SERVER_PORT;
        }

        if (isValidPort(mjpegServerPort)) {
            this.mjpegServerPort = mjpegServerPort;
        } else {
            Logger.warn(String.format(
                "The MJPEG server port is out of valid range [%s;%s]: %s -- using default: %s",
                MIN_PORT,
                MAX_PORT,
                mjpegServerPort,
                ServerConfig.DEFAULT_MJPEG_SERVER_PORT
            ));
            this.mjpegServerPort = ServerConfig.DEFAULT_MJPEG_SERVER_PORT;
        }

        this.powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        setAccessibilityServiceState();
    }

    public static synchronized ServerInstrumentation getInstance() {
        if (instance == null) {
            instance = new ServerInstrumentation(
                getApplicationContext(),
                getServerPort(),
                getMjpegServerPort()
            );
        }
        return instance;
    }

    private void releaseWakeLock() {
        Logger.debug(String.format(
                "Got request to release the wake lock (current value %s, timeout %s)",
                wakeLock, wakeLockTimeoutMs));

        if (wakeLock == null) {
            return;
        }

        try {
            wakeLock.release();
        } catch (Exception e) {/* ignore */}
        wakeLock = null;
        wakeLockAcquireTimestampMs = 0;
        wakeLockTimeoutMs = 0;
    }

    public long getWakeLockTimeout() {
        return (wakeLock == null || !wakeLock.isHeld() || wakeLockAcquireTimestampMs <= 0 || wakeLockTimeoutMs <= 0)
                ? 0
                : wakeLockAcquireTimestampMs + wakeLockTimeoutMs - SystemClock.elapsedRealtime();
    }

    public void acquireWakeLock(long msTimeout) {
        Logger.debug(String.format(
                "Got request to acquire a new wake lock with %sms timeout", msTimeout));

        releaseWakeLock();

        if (msTimeout <= 0) {
            return;
        }

        // Get a wake lock to stop the cpu going to sleep
        //noinspection deprecation
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, WAKE_LOCK_TAG);
        try {
            wakeLock.acquire(msTimeout);
            wakeLockAcquireTimestampMs = SystemClock.elapsedRealtime();
            wakeLockTimeoutMs = msTimeout;
            getUiDevice().wakeUp();
            Logger.debug(String.format(
                    "Successfully acquired the wake lock with %sms timeout", msTimeout));
        } catch (Exception e) {
            if (wakeLock.isHeld()) {
                Logger.error("Error while waking up the device", e);
            } else {
                Logger.error("Cannot acquire the wake lock", e);
                wakeLock = null;
                wakeLockAcquireTimestampMs = 0;
                wakeLockTimeoutMs = 0;
            }
        }
    }

    public boolean isServerStopped() {
        return isServerStopped;
    }

    private boolean isValidPort(int port) {
        return port >= MIN_PORT && port <= MAX_PORT;
    }

    public void stopServer() {
        try {
            releaseWakeLock();
            stopServerThread();
        } finally {
            instance = null;
        }
    }

    public void startServer() throws SessionRemovedException {
        if (serverThread != null && serverThread.isAlive()) {
            return;
        }

        if (serverThread == null && isServerStopped) {
            throw new SessionRemovedException("Delete Session has been invoked");
        }

        if (serverThread != null) {
            Logger.error("Stopping UiAutomator2 io.appium.uiautomator2.http io.appium.uiautomator2.server");
            stopServer();
        }

        serverThread = new HttpdThread(this.serverPort);
        serverThread.start();

        //client to wait for io.appium.uiautomator2.server to up
        Logger.info("io.appium.uiautomator2.server started:");
    }

    private void stopServerThread() {
        if (serverThread == null) {
            return;
        }

        if (!serverThread.isAlive()) {
            serverThread = null;
            return;
        }

        Logger.info("Stopping uiautomator2 io.appium.uiautomator2.http io.appium.uiautomator2.server");
        serverThread.stopLooping();
        serverThread.interrupt();
        try {
            serverThread.join();
        } catch (InterruptedException ignored) {
        }
        serverThread = null;
        isServerStopped = true;
    }

    public void startMjpegServer() {
        if (mjpegScreenshotServerThread != null && mjpegScreenshotServerThread.isAlive()) {
            return;
        }

        Logger.info("Starting MJPEG Server");
        mjpegScreenshotServerThread = new MjpegScreenshotServer(mjpegServerPort);
        mjpegScreenshotServerThread.start();
        Logger.info("MJPEG Server started");
    }

    public void stopMjpegServer() {
        if (mjpegScreenshotServerThread == null || !mjpegScreenshotServerThread.isAlive()) {
            return;
        }

        Logger.info("Stopping MJPEG Server");
        mjpegScreenshotServerThread.interrupt();
        try {
            mjpegScreenshotServerThread.join();
        } catch (InterruptedException ignored) {
            // swallow
        }
        mjpegScreenshotServerThread = null;
        Logger.info("MJPEG Server stoppped");
    }

    public static class PowerConnectionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logger.debug("Received broadcast action: " + intent.getAction());

            if (!ACTION_POWER_DISCONNECTED.equalsIgnoreCase(intent.getAction())) {
                return;
            }

            if (instance == null) {
                Logger.debug("The server is already down - doing nothing.");
                return;
            }

            final ShutdownOnPowerDisconnect shutdownOnPowerDisconnect =
                    (ShutdownOnPowerDisconnect) Settings.SHUTDOWN_ON_POWER_DISCONNECT.getSetting();
            if (!shutdownOnPowerDisconnect.getValue()) {
                Logger.debug(String.format("The value of `%s` setting is false - " +
                        "ignoring broadcasting.", shutdownOnPowerDisconnect.getName()));
                return;
            }

            Logger.info("The device was disconnected from power source. Shutting down the server.");
            getInstance().stopMjpegServer();
            getInstance().stopServer();
        }
    }

    private class HttpdThread extends Thread {
        private final AndroidServer server;
        private Looper looper;

        public HttpdThread(int serverPort) {
            // Create the io.appium.uiautomator2.server but absolutely do not start it here
            server = new AndroidServer(serverPort);
        }

        @Override
        public void run() {
            Looper.prepare();
            looper = Looper.myLooper();
            startServer();
            Looper.loop();
        }

        @Override
        public void interrupt() {
            server.stop();
            super.interrupt();
        }

        public AndroidServer getServer() {
            return server;
        }

        private void startServer() {
            acquireWakeLock(MAX_TEST_DURATION);

            server.start();

            Logger.info("Started UiAutomator2 io.appium.uiautomator2.http io.appium.uiautomator2.server on port " + server.getPort());
        }

        public void stopLooping() {
            if (looper == null) {
                return;
            }
            looper.quit();
        }
    }
}
