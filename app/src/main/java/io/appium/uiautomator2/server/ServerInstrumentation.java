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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;

import io.appium.uiautomator2.common.exceptions.SessionRemovedException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.ShutdownOnPowerDisconnect;
import io.appium.uiautomator2.utils.Logger;

import static android.content.Intent.ACTION_POWER_DISCONNECTED;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
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
    private HttpdThread serverThread;
    private PowerManager.WakeLock wakeLock;
    private boolean isServerStopped;

    public ServerInstrumentation(Context context, int serverPort) {
        if (!isValidPort(serverPort)) {
            throw new UiAutomator2Exception(String.format(
                    "The port is out of valid range [%s;%s]: %s", MIN_PORT, MAX_PORT, serverPort));
        }
        this.serverPort = serverPort;
        this.powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    public static synchronized ServerInstrumentation getInstance() {
        if (instance == null) {
            instance = new ServerInstrumentation(getApplicationContext(), getServerPort());
        }
        return instance;
    }

    private void releaseWakeLock() {
        if (wakeLock == null) {
            return;
        }

        try {
            wakeLock.release();
        } catch (Exception e) {/* ignore */}
        wakeLock = null;
    }

    private void acquireWakeLock() {
        releaseWakeLock();

        // Get a wake lock to stop the cpu going to sleep
        //noinspection deprecation
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, WAKE_LOCK_TAG);
        try {
            wakeLock.acquire(MAX_TEST_DURATION);
            getUiDevice().wakeUp();
        } catch (SecurityException e) {
            Logger.error("Security Exception", e);
        } catch (RemoteException e) {
            Logger.error("Remote Exception while waking up", e);
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
            acquireWakeLock();

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
