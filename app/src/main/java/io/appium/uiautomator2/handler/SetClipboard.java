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

public class SetClipboard extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = InstrumentationRegistry.getInstrumentation();

    public SetClipboard(String mappedUri) {
        super(mappedUri);
    }

    private static String fromBase64String(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT), StandardCharsets.UTF_8);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Logger.info("Set Clipboard command");
        final String content;
        ClipDataType contentType = ClipDataType.PLAINTEXT;
        String label = null;
        JSONObject payload = getPayload(request);
        try {
            content = fromBase64String(payload.getString("content"));
            if (payload.has("contentType")) {
                contentType = ClipDataType.valueOf(payload
                        .getString("contentType")
                        .toUpperCase());
            }
            if (payload.has("label")) {
                label = payload.getString("label");
            }

            mInstrumentation.runOnMainSync(new AppiumSetClipboardRunnable(contentType, label, content));
        } catch (IllegalArgumentException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                    String.format("Only '%s' content types are supported. '%s' is given instead",
                            ClipDataType.supportedDataTypes(),
                            contentType));
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS);
    }

    // Clip feature should run with main thread
    private class AppiumSetClipboardRunnable implements Runnable {
        private ClipDataType contentType;
        private String label;
        private String content;

        AppiumSetClipboardRunnable(ClipDataType contentType, String label, String content) {
            this.contentType = contentType;
            this.label = label;
            this.content = content;
        }

        @Override
        public void run() {
            switch (contentType) {
                case PLAINTEXT:
                    new ClipboardHelper(mInstrumentation.getTargetContext()).setTextData(label, content);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
