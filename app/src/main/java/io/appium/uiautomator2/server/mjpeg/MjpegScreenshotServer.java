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
import java.util.List;
import java.util.Locale;

import io.appium.uiautomator2.utils.Logger;

public class MjpegScreenshotServer extends Thread {
    private final int port;
    private final List<MjpegScreenshotClient> clients;
    private final MjpegScreenshotStream mjpegScreenshotStream;
    private ServerSocket serverSocket;
    private boolean isStopped = false;

    public MjpegScreenshotServer(int port) {
        this(port, new ArrayList<MjpegScreenshotClient>());
    }

    public MjpegScreenshotServer(int port, List<MjpegScreenshotClient> clients) {
        this.port = port;
        this.clients = clients;
        this.mjpegScreenshotStream = new MjpegScreenshotStream(clients);
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
        while (!isStopped) {
            try {
                Logger.debug("Socket Server waiting for connections.");
                addClient(new MjpegScreenshotClient(serverSocket.accept()));
            } catch (IOException e) {
                Logger.error("Socket Server failed to open connection.", e);
            }
        }

        closeAllClients();
    }

    private void closeServer() {
        if (isStopped) {
            return;
        }

        closeAllClients();

        try {
            serverSocket.close();
        } catch (IOException e) {
            Logger.error("Socket Server failed to close socket.", e);
        }

        this.isStopped = true;
    }

    private void addClient(MjpegScreenshotClient client) {
        synchronized (clients) {
            clients.add(client);
        }
    }

    private void closeAllClients() {
        synchronized (clients) {
            for (MjpegScreenshotClient client : clients) {
                client.closeSocket();
            }
        }
    }
}
