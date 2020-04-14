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

import java.lang.Math;
import java.util.List;
import java.util.Iterator;
import java.util.Locale;

import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.server.ServerConfig;
import io.appium.uiautomator2.utils.ScreenshotHelper;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MjpegScreenshotStream extends Thread {
    private static final UiAutomation uia =
        CustomUiDevice.getInstance().getInstrumentation().getUiAutomation();
    private final List<MjpegScreenshotClient> clients;
    private boolean stopped = false;

    MjpegScreenshotStream(List<MjpegScreenshotClient> clients) {
        this.clients = clients;
    }

    @Override
    public void interrupt() {
        this.stopped = true;
        super.interrupt();
    }

    @Override
    public void run() {
        while (!stopped) {
            if (clients.size() == 0) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            // how long each loop should take in milliseconds
            long targetInterval =
                Math.round((1.0f / ServerConfig.getMjpegServerFramerate()) * 1000.0f);
            long start = System.currentTimeMillis();

            byte[] screenshotData = getScreenshot();
            if (screenshotData.length > 0) {
                MjpegScreenshotClient client;
                for (Iterator<MjpegScreenshotClient> iterator = clients.iterator(); iterator.hasNext();) {
                    client = iterator.next();
                    if (client.getClosed()) {
                        iterator.remove();
                        continue;
                    }

                    if (!client.getInitialized()) {
                        client.initialize();
                    }

                    client.write(screenshotData);
                }
            }
            matchFramerate(targetInterval, start);
        }
    }

    private void matchFramerate(long targetInterval, long start) {
        long end = System.currentTimeMillis();
        long duration = end - start;

        if (duration < targetInterval) {
            try {
                sleep(targetInterval - duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] getScreenshot() {
        Bitmap screenshot = uia.takeScreenshot();
        byte[] jpeg = ScreenshotHelper.compressJpeg(
            screenshot,
            ServerConfig.getMjpegScalingFactor() / 100.0f,
            ServerConfig.getMjpegServerScreenshotQuality(),
            ServerConfig.getMjpegFiltering()
        );
        screenshot.recycle();

        byte[] header = String.format(
            Locale.ROOT,
            "--BoundaryString\r\nContent-type: image/jpg\r\nContent-Length: %d\r\n\r\n",
            jpeg.length
        ).getBytes(UTF_8);
        byte[] end = "\r\n\r\n".getBytes(UTF_8);
        byte[] data = new byte[jpeg.length + header.length + end.length];

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
            end,
            0,
            data,
            header.length + jpeg.length,
            end.length
        );

        return data;
    }
}
