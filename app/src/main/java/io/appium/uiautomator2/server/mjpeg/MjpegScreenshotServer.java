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

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.appium.uiautomator2.utils.Logger;

public class MjpegScreenshotServer extends Thread {
    private final List<MjpegScreenshotClient> clients =
        Collections.synchronizedList(new ArrayList<MjpegScreenshotClient>());
    private final MjpegScreenshotStream mjpegScreenshotStream =
        new MjpegScreenshotStream(clients);
    private boolean stopped = false;
    private int port;
    private ServerSocket serverSocket;

    public MjpegScreenshotServer(int port) {
        this.port = port;
    }

    @Override
    public void interrupt() {
        closeServer();
        super.interrupt();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            Logger.info(String.format(
                Locale.ROOT,
                "ServerSocket created on port %d", port));
        } catch (IOException e) {
            Logger.error("Failed to create Socket Server.", e);
            return;
        }

        mjpegScreenshotStream.start();
        while (!stopped) {
            try {
                Logger.debug("Socket Server waiting for connections.");
                MjpegScreenshotClient newClient =
                    new MjpegScreenshotClient(serverSocket.accept());
                clients.add(newClient);
            } catch (IOException e) {
                Logger.error("Socket Server failed to open connection.", e);
            }
        }

        closeAllClients();
    }

    private void closeServer() {
        closeAllClients();

        try {
            serverSocket.close();
        } catch (IOException e) {
            Logger.error("Socket Server failed to close socket.", e);
        }

        this.stopped = true;
    }

    private void closeAllClients() {
        for (MjpegScreenshotClient client : clients) {
            client.closeSocket();
        }
    }
}
