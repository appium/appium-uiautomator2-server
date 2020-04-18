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

package io.appium.uiautomator2.server.mjpeg;

import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.os.SystemClock;

import java.lang.Math;
import java.util.List;
import java.util.Iterator;
import java.util.Locale;

import io.appium.uiautomator2.common.exceptions.TakeScreenshotException;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.server.ServerConfig;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.ScreenshotHelper;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MjpegScreenshotStream extends Thread {
    private static final String BOUNDARY_STRING = 
        "--BoundaryString\r\n" +
        "Content-type: image/jpg\r\n" +
        "Content-Length: %d\r\n\r\n";
    private static final int NO_CLIENTS_CONNECTED_SLEEP_TIME_MS = 500;
    private static final byte[] END = "\r\n\r\n".getBytes(UTF_8);
    private static final UiAutomation UI_AUTOMATION =
        CustomUiDevice.getInstance().getInstrumentation().getUiAutomation();
    private final List<MjpegScreenshotClient> clients;
    private boolean isStopped = false;

    MjpegScreenshotStream(List<MjpegScreenshotClient> clients) {
        this.clients = clients;
    }

    @Override
    public void interrupt() {
        this.isStopped = true;
        super.interrupt();
    }

    @Override
    public void run() {
        while (!isStopped) {
            boolean noClientsConnected;
            synchronized (clients) {
                noClientsConnected = clients.isEmpty();
            }

            if (noClientsConnected) {
                SystemClock.sleep(NO_CLIENTS_CONNECTED_SLEEP_TIME_MS);
                continue;
            }

            // how long each loop should take in milliseconds
            long targetInterval =
                Math.round((1.0f / ServerConfig.getMjpegServerFramerate()) * 1000.0f);
            long start = System.currentTimeMillis();

            // add some resilience to handle `getScreenshot` or any of its
            // sub-routines raising an error. If an error occurs during
            // [post-]processing the frame will be dropped
            byte[] screenshotData;
            try {
                screenshotData = getScreenshot();
            } catch (Exception e) {
                Logger.error("Error getting screenshot: ", e);
                screenshotData = new byte[0];
            }

            if (screenshotData.length > 0) {
                synchronized (clients) {
                    Iterator<MjpegScreenshotClient> clientsIterator = clients.iterator();
                    while (clientsIterator.hasNext()) {
                        MjpegScreenshotClient client = clientsIterator.next();
                        if (client.isClosed()) {
                            clientsIterator.remove();
                            continue;
                        }

                        if (!client.isInitialized()) {
                            client.initialize();
                        }

                        client.write(screenshotData);
                    }
                }
            } else {
                Logger.warn("Empty screenshot returned, dropping frame");
            }

            // Always attempt to match the target framerate, even if the frame
            // was dropped, to keep the rate consistent
            matchFramerate(targetInterval, start);
        }
    }

    private void matchFramerate(long targetInterval, long start) {
        long end = System.currentTimeMillis();
        long duration = end - start;

        if (duration < targetInterval) {
            SystemClock.sleep(targetInterval - duration);
        }
    }

    private byte[] getScreenshot() {
        Bitmap screenshot = UI_AUTOMATION.takeScreenshot();
        if (screenshot == null) {
            throw new TakeScreenshotException("Could not take screenshot: UiAutomation returned null");
        }

        byte[] jpeg = ScreenshotHelper.compressJpeg(
            screenshot,
            ServerConfig.getMjpegScalingFactor() / 100.0f,
            ServerConfig.getMjpegServerScreenshotQuality(),
            ServerConfig.isMjpegBilinearFiltering()
        );
        screenshot.recycle();

        byte[] header = String.format(
            Locale.ROOT,
            BOUNDARY_STRING,
            jpeg.length
        ).getBytes(UTF_8);
        byte[] data = new byte[jpeg.length + header.length + END.length];

        System.arraycopy(
            header,
            0,
            data,
            0,
            header.length
        );
        System.arraycopy(
            jpeg,
            0,
            data,
            header.length,
            jpeg.length
        );
        System.arraycopy(
            END,
            0,
            data,
            header.length + jpeg.length,
            END.length
        );

        return data;
    }
}
