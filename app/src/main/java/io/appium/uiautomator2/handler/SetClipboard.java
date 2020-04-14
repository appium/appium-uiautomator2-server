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
import android.util.Base64;

import io.appium.uiautomator2.model.api.ClipboardModel;

import java.nio.charset.StandardCharsets;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.ClipboardHelper;
import io.appium.uiautomator2.utils.ClipboardHelper.ClipDataType;
import io.appium.uiautomator2.utils.Logger;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.ModelValidators.requireString;

public class SetClipboard extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = getInstrumentation();

    public SetClipboard(String mappedUri) {
        super(mappedUri);
    }

    private static String fromBase64String(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT), StandardCharsets.UTF_8);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Set Clipboard command");
        ClipDataType contentType = ClipDataType.PLAINTEXT;
        ClipboardModel model = toModel(request, ClipboardModel.class);
        try {
            String content = fromBase64String(requireString(model,"content"));
            if (model.contentType != null) {
                contentType = ClipDataType.valueOf(model.contentType.toUpperCase());
            }
            String label = model.label;

            mInstrumentation.runOnMainSync(new AppiumSetClipboardRunnable(contentType, label, content));
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException(
                    String.format("Only '%s' content types are supported. '%s' is given instead",
                            ClipDataType.supportedDataTypes(),
                            contentType));
        }
        return new AppiumResponse(getSessionId(request));
    }

    // Clip feature should run with main thread
    private class AppiumSetClipboardRunnable implements Runnable {
        private final ClipDataType contentType;
        private final String label;
        private final String content;

        AppiumSetClipboardRunnable(ClipDataType contentType, String label, String content) {
            this.contentType = contentType;
            this.label = label;
            this.content = content;
        }

        @Override
        public void run() {
            if (contentType != ClipDataType.PLAINTEXT) {
                throw new IllegalArgumentException();
            }
            new ClipboardHelper(mInstrumentation.getTargetContext()).setTextData(label, content);
        }
    }
}
