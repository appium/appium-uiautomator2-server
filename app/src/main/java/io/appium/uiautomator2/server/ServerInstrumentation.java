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

import androidx.annotation.Nullable;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.Configurator;

import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.ShutdownOnPowerDisconnect;
import io.appium.uiautomator2.server.mjpeg.MjpegScreenshotServer;
import io.appium.uiautomator2.test.BuildConfig;
import io.appium.uiautomator2.utils.Logger;

import static android.content.Intent.ACTION_POWER_DISCONNECTED;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static io.appium.uiautomator2.utils.Device.getUiDevice;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ServerInstrumentation {
    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 65535;
    private static final String WAKE_LOCK_TAG = "UiAutomator2:ScreenKeeper";
    public static final long MAX_TEST_DURATION = 24 * 60 * 60 * 1000;

    private static ServerInstrumentation instance;

    private final PowerManager powerManager;
    private final int httpServerPort;
    private final int mjpegServerPort;
    private HttpdThread httpServerThread;
    private MjpegScreenshotServer mjpegScreenshotServerThread;
    private PowerManager.WakeLock wakeLock;
    private long wakeLockAcquireTimestampMs = 0;
    private long wakeLockTimeoutMs = 0;
    private CountDownLatch shutdownLatch;

    private ServerInstrumentation() {
        this.httpServerPort = requireValidPort(
                ServerConfig.getServerPort(), ServerConfig.DEFAULT_SERVER_PORT, "server"
        );
        this.mjpegServerPort = requireValidPort(
                ServerConfig.getMjpegServerPort(), ServerConfig.DEFAULT_MJPEG_SERVER_PORT, "MJPEG"
        );
        this.powerManager = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        setAccessibilityServiceState();
    }

    public static synchronized ServerInstrumentation getInstance() {
        if (instance == null) {
            instance = new ServerInstrumentation();
        }
        return instance;
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

        if (msTimeout <= 0 || powerManager == null) {
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

    public void stop() {
        synchronized (WAKE_LOCK_TAG) {
            if (shutdownLatch == null) {
                return;
            }

            try {
                Logger.info("Stopping ServerInstrumentation");
                stopMjpegServer();
                releaseWakeLock();
                stopHttpServer();
                Logger.info("ServerInstrumentation stopped");
            } finally {
                if (null != shutdownLatch) {
                    shutdownLatch.countDown();
                    shutdownLatch = null;
                }
            }
        }
    }

    public void start() {
        synchronized (WAKE_LOCK_TAG) {
            if (shutdownLatch != null) {
                return;
            }

            Logger.info("Starting ServerInstrumentation");
            Logger.info("buildConfig: ",new Gson().toJson(getBuildConfig()));
            shutdownLatch = new CountDownLatch(1);
            acquireWakeLock(MAX_TEST_DURATION);
            startHttpServer();
            startMjpegServer();
            //client to wait for io.appium.uiautomator2.server to up
            Logger.info("ServerInstrumentation started");
        }
    }

    @Nullable
    public CountDownLatch getShutdownLatch() {
        return shutdownLatch;
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

            final ShutdownOnPowerDisconnect shutdownOnPowerDisconnect = Settings.get(ShutdownOnPowerDisconnect.class);
            if (!shutdownOnPowerDisconnect.getValue()) {
                Logger.debug(String.format("The value of `%s` setting is false - " +
                        "ignoring broadcasting.", shutdownOnPowerDisconnect.getName()));
                return;
            }

            Logger.info("The device was disconnected from power source. Shutting down the server.");
            getInstance().stop();
        }
    }

    private void startHttpServer() {
        if (httpServerThread != null) {
            return;
        }

        httpServerThread = new HttpdThread(this.httpServerPort);
        httpServerThread.start();
    }

    private void stopHttpServer() {
        try {
            if (httpServerThread == null || !httpServerThread.isAlive()) {
                return;
            }

            httpServerThread.interrupt();
            try {
                httpServerThread.join();
            } catch (InterruptedException ignored) {
            }
        } finally {
            httpServerThread = null;
        }
    }

    private static int requireValidPort(int port, int defaultPort, String entityName) {
        if (port >= MIN_PORT && port <= MAX_PORT) {
            return port;
        }

        Logger.warn(String.format(
                "The %s port is out of valid range [%s;%s]: %s -- using default: %s",
                entityName,
                MIN_PORT,
                MAX_PORT,
                port,
                defaultPort
        ));
        return defaultPort;
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
        } catch (Exception e) {
            /* ignore */
        } finally {
            wakeLock = null;
            wakeLockAcquireTimestampMs = 0;
            wakeLockTimeoutMs = 0;
        }
    }

    private void startMjpegServer() {
        if (mjpegScreenshotServerThread != null && mjpegScreenshotServerThread.isAlive()) {
            return;
        }

        Logger.info("Starting MJPEG Server");
        mjpegScreenshotServerThread = new MjpegScreenshotServer(mjpegServerPort);
        mjpegScreenshotServerThread.start();
        Logger.info("MJPEG Server started");
    }

    private void stopMjpegServer() {
        try {
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
            Logger.info("MJPEG Server stopped");
        } finally {
            mjpegScreenshotServerThread = null;
        }
    }

    private void setAccessibilityServiceState() {
        String disableSuppressAccessibilityService = InstrumentationRegistry.getArguments()
                .getString("DISABLE_SUPPRESS_ACCESSIBILITY_SERVICES");
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

    private static class HttpdThread extends Thread {
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
            server.start();
            Looper.loop();
        }

        @Override
        public void interrupt() {
            if (looper != null) {
                looper.quit();
                looper = null;
            }

            server.stop();
            super.interrupt();
        }
    }
    public static Map<String, Object> getBuildConfig() {
        final Map<String, Object> fieldMap = new HashMap<>();

        for (Field field : BuildConfig.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(null);
                    fieldMap.put(field.getName(), value);
                } catch (IllegalAccessException e) {
                    Logger.error("Field access denied for: " + field.getName(), e);
                }
            }
        }
        return fieldMap;
    }
}
