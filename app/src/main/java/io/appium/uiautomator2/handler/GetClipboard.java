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

package io.appium.uiautomator2.handler;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.ClipboardHelper;
import io.appium.uiautomator2.utils.ClipboardHelper.ClipDataType;
import io.appium.uiautomator2.utils.Logger;

public class GetClipboard extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = InstrumentationRegistry.getInstrumentation();

    public GetClipboard(String mappedUri) {
        super(mappedUri);
    }

    private static String toBase64String(String s) {
        return Base64.encodeToString(s.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Logger.info("Get Clipboard command");
        ClipDataType contentType = ClipDataType.PLAINTEXT;
        try {
            JSONObject payload = getPayload(request);
            if (payload.has("contentType")) {
                contentType = ClipDataType.valueOf(payload
                        .getString("contentType")
                        .toUpperCase());
            }
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,
                    getClipboardResponse(contentType));
        } catch (IllegalArgumentException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                    String.format("Only '%s' content types are supported. '%s' is given instead",
                            ClipDataType.supportedDataTypes(),
                            contentType));
        }
    }

    // Clip feature should run with main thread
    private String getClipboardResponse(ClipDataType contentType) {
        AppiumGetClipboardRunnable runnable = new AppiumGetClipboardRunnable(contentType);
        mInstrumentation.runOnMainSync(runnable);
        return runnable.getContent();
    }

    private class AppiumGetClipboardRunnable implements Runnable {
        private ClipDataType contentType;
        private String content;

        AppiumGetClipboardRunnable(ClipDataType contentType) {
            this.contentType = contentType;
        }

        @Override
        public void run() {
            switch (contentType) {
                case PLAINTEXT:
                    content = toBase64String(new ClipboardHelper(mInstrumentation.getTargetContext()).getTextData());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public String getContent() {
            return content;
        }
    }
}
