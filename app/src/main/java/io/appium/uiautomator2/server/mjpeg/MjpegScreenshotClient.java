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

import io.appium.uiautomator2.utils.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

class MjpegScreenshotClient {
    private final Socket socket;
    private boolean closed = false;
    private boolean initialized = false;
    private OutputStream out;
    private BufferedReader in;

    MjpegScreenshotClient(Socket socket) {
        this.socket = socket;
    }

    boolean getInitialized() {
        return initialized;
    }

    boolean getClosed() {
        return closed;
    }

    private String getRemoteAddress() {
        if (socket == null) {
            return "";
        }
        return socket.getRemoteSocketAddress().toString().replaceAll("^/+", "");
    }

    void closeSocket() {
        try {
            socket.close();
        } catch (IOException closeError) {
            Logger.error("Error closing socket.", closeError);
        }

        this.closed = true;
    }

    void initialize() {
        Logger.info(String.format(
            Locale.ROOT,
            "Screenshot broadcast client opened a connection %s",
            getRemoteAddress()
        ));

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = socket.getOutputStream();
        } catch (IOException e) {
            Logger.error("Client failed to initialize");
            closeSocket();
            return;
        }

        try {
            String line = in.readLine();
            while (line == null) {
                line = in.readLine();
            }

            Logger.info(String.format(
                Locale.ROOT,
                "Screenshot broadcast starting for %s",
                getRemoteAddress()
            ));
            String start = "HTTP/1.0 200 OK\r\nServer: AQI Android Screenshot Socket Server\r\nConnection: close\r\nMax-Age: 0\r\nExpires: 0\r\nCache-Control: no-cache, private\r\nPragma: no-cache\r\nContent-Type: multipart/x-mixed-replace; boundary=--BoundaryString\r\n\r\n";
            out.write(start.getBytes(UTF_8));
        } catch (IOException e) {
            Logger.info("Client socket connection could not be read.", e);
            closeSocket();
            return;
        }

        this.initialized = true;
    }

    void write(byte[] data) {
        try {
            out.write(data);
        } catch (IOException e) {
            Logger.info("Client socket connection not writable. Closing... ", e);
            closeSocket();
        }
    }
}
