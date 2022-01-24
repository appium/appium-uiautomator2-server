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
package io.appium.uiautomator2.unittest.test.internal;

import android.os.SystemClock;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import java.io.IOException;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.unittest.test.Config;

import static android.os.SystemClock.elapsedRealtime;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class Client {

    private static final MediaType JSON = MediaType.parse("application/json; " + "charset=utf-8");
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    static {
        final int timeout = 15 * 1000;
        HTTP_CLIENT.setConnectTimeout(timeout, SECONDS);
        HTTP_CLIENT.setReadTimeout(timeout, SECONDS);
        HTTP_CLIENT.setWriteTimeout(timeout, SECONDS);
    }

    public static Response get(final String path) {
        return get(WebDriverSession.getInstance().getBaseUrl(), path);
    }

    private static Response get(final String baseUrl, final String path) {
        Request request = new Request.Builder().url(baseUrl + path).build();
        return execute(request);
    }

    public static Response post(final String path, final JSONObject body) {
        return post(WebDriverSession.getInstance().getBaseUrl(), path, body);
    }

    public static Response post(final String baseUrl, final String path, final JSONObject body) {
        Request request = new Request.Builder().url(baseUrl + path)
                .post(RequestBody.create(JSON, body.toString())).build();
        return execute(request);
    }

    public static Response delete() {
        Request request = new Request.Builder().url(WebDriverSession.getInstance().getBaseUrl())
                .delete(RequestBody.create(JSON, new JSONObject().toString())).build();
        return execute(request);
    }

    public static void waitForNettyStatus(final NettyStatus status) {
        Logger.debug("Waiting until netty server will be " + status);
        long start = elapsedRealtime();
        NettyStatus actualStatus;
        do {
            try {
                get(Config.HOST, "/status");
                actualStatus = NettyStatus.ONLINE;
            } catch (Exception e) {
                actualStatus = NettyStatus.OFFLINE;
            }
            Logger.info("Netty server status is " + actualStatus);
            if (actualStatus.equals(status)) {
                return;
            }
            SystemClock.sleep(500);
        } while (elapsedRealtime() - start < Config.NETTY_STATUS_TIMEOUT);
        throw new TimeoutException(String.format("netty status. Expected:%s; Actual:%s;",
                status, actualStatus));
    }

    private static Response execute(final Request request) {
        try {
            return new Response(HTTP_CLIENT.newCall(request).execute());
        } catch (IOException e) {
            throw new UiAutomator2Exception(request.method() + " \"" + request.urlString() + "\" " +
                    "failed. ", e);
        }
    }

}
