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
import static android.support.test.InstrumentationRegistry.getContext;
import static io.appium.uiautomator2.server.ServerConfig.getServerPort;
import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class ServerInstrumentation {
    private static final int MIN_PORT = 1024;
    private static final int MAX_PORT = 65535;

    private static ServerInstrumentation instance;

    private final Context context;
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
        this.context = context;
    }

    public static synchronized ServerInstrumentation getInstance() {
        if (instance == null) {
            instance = new ServerInstrumentation(getContext(), getServerPort());
        }
        return instance;
    }

    public boolean isServerStopped() {
        return isServerStopped;
    }

    private boolean isValidPort(int port) {
        return port >= MIN_PORT && port <= MAX_PORT;
    }

    public void stopServer() {
        try {
            if (wakeLock != null) {
                try {
                    wakeLock.release();
                } catch (Exception e) {/* ignore */}
                wakeLock = null;
            }
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
            // Get a wake lock to stop the cpu going to sleep
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "UiAutomator2");
            try {
                wakeLock.acquire();
                getUiDevice().wakeUp();
            } catch (SecurityException e) {
                Logger.error("Security Exception", e);
            } catch (RemoteException e) {
                Logger.error("Remote Exception while waking up", e);
            }

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
