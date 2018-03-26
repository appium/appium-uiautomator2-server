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

    private static String fromBase64String(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT), StandardCharsets.UTF_8);
    }

    public SetClipboard(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Set Clipboard command");
        final String content;
        ClipDataType contentType = ClipDataType.PLAINTEXT;
        String label = null;
        try {
            JSONObject payload = getPayload(request);
            content = fromBase64String(payload.getString("content"));
            if (payload.has("contentType")) {
                contentType = ClipDataType.valueOf(payload
                        .getString("contentType")
                        .toUpperCase());
            }
            if (payload.has("label")) {
                label = payload.getString("label");
            }
            switch (contentType) {
                case PLAINTEXT:
                    new ClipboardHelper(mInstrumentation.getTargetContext())
                            .setTextData(label, content);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                    String.format("Only '%s' content types are supported. '%s' is given instead",
                            ClipDataType.supportedDataTypes(),
                            contentType));
        } catch (Exception e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS);
    }

}
