package io.appium.uiautomator2.server.test;

import android.os.SystemClock;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.runner.AndroidJUnit4;
import io.appium.uiautomator2.common.exceptions.SessionRemovedException;
import io.appium.uiautomator2.server.ServerInstrumentation;
import io.appium.uiautomator2.utils.Logger;

@RunWith(AndroidJUnit4.class)
public class AppiumUiAutomator2Server {
    private static ServerInstrumentation serverInstrumentation;

    /**
     * Starts the server on the device.
     * !!! This class is the main entry point for UIA2 driver package.
     * !!! Do not rename or move it unless you know what you are doing.
     */
    @Test
    public void startServer() {
        if (serverInstrumentation == null) {
            serverInstrumentation = ServerInstrumentation.getInstance();
            Logger.info("[AppiumUiAutomator2Server]", " Starting Server");
            try {
                while (!serverInstrumentation.isServerStopped()) {
                    SystemClock.sleep(1000);
                    serverInstrumentation.startMjpegServer();
                    serverInstrumentation.startServer();
                }
            } catch (SessionRemovedException e) {
                //Ignoring SessionRemovedException
            }
        }
    }
}

