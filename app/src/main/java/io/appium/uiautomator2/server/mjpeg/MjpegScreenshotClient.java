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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;

import android.os.SystemClock;

import io.appium.uiautomator2.utils.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

class MjpegScreenshotClient {
    private static final int INPUT_NOT_READY_SLEEP_TIME_MS = 100;
    private static final byte[] START = (
        "HTTP/1.0 200 OK\r\n" +
        "Server: Android Screenshot Socket Server\r\n" +
        "Connection: close\r\n" +
        "Max-Age: 0\r\n" +
        "Expires: 0\r\n" +
        "Cache-Control: no-cache, private\r\n" +
        "Pragma: no-cache\r\n" +
        "Content-Type: multipart/x-mixed-replace; boundary=--BoundaryString\r\n\r\n"
    ).getBytes(UTF_8);

    private final Socket socket;
    private BufferedReader in;
    private OutputStream out;
    private boolean isClosed = false;
    private boolean isInitialized = false;

    MjpegScreenshotClient(Socket socket) {
        this.socket = socket;
    }

    private String getRemoteAddress() {
        if (socket == null) {
            return "";
        }
        return socket.getRemoteSocketAddress().toString().replaceAll("^/+", "");
    }

    boolean isClosed() {
        return isClosed;
    }

    boolean isInitialized() {
        return isInitialized;
    }

    void closeSocket() {
        if (isClosed()) {
            return;
        }

        try {
            socket.close();
        } catch (IOException closeError) {
            Logger.error("Error closing socket.", closeError);
        }

        this.isClosed = true;
    }

    void initialize() {
        if (isInitialized()) {
            return;
        }

        Logger.info(String.format(
            Locale.ROOT,
            "Screenshot broadcast client opened a connection %s",
            getRemoteAddress()
        ));

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = socket.getOutputStream();
        } catch (IOException e) {
            Logger.error("Client failed to initialize", e);
            closeSocket();
            return;
        }

        try {
            while (!in.ready()) {
               SystemClock.sleep(INPUT_NOT_READY_SLEEP_TIME_MS);
            }

            Logger.info(String.format(
                Locale.ROOT,
                "Screenshot broadcast starting for %s",
                getRemoteAddress()
            ));
            out.write(START);
        } catch (IOException e) {
            Logger.warn("Client socket connection could not be read.", e);
            closeSocket();
            return;
        }

        this.isInitialized = true;
    }

    void write(byte[] data) {
        try {
            out.write(data);
        } catch (IOException e) {
            Logger.warn("Client socket connection not writable. Closing... ", e);
            closeSocket();
        }
    }
}
