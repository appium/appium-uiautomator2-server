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

import io.appium.uiautomator2.model.api.GetClipboardModel;

import java.nio.charset.StandardCharsets;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.ClipboardHelper;
import io.appium.uiautomator2.utils.ClipboardHelper.ClipDataType;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class GetClipboard extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = getInstrumentation();

    public GetClipboard(String mappedUri) {
        super(mappedUri);
    }

    private static String toBase64String(String s) {
        return Base64.encodeToString(s.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        ClipDataType contentType = ClipDataType.PLAINTEXT;
        GetClipboardModel model = toModel(request, GetClipboardModel.class);
        try {
            if (model.contentType != null) {
                contentType = ClipDataType.valueOf(model.contentType.toUpperCase());
            }
            return new AppiumResponse(getSessionId(request), getClipboardResponse(contentType));
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException(
                    String.format("Only '%s' content types are supported. '%s' is given instead",
                            ClipDataType.supportedDataTypes(), contentType));
        }
    }

    // Clip feature should run with main thread
    private String getClipboardResponse(ClipDataType contentType) {
        AppiumGetClipboardRunnable runnable = new AppiumGetClipboardRunnable(contentType);
        mInstrumentation.runOnMainSync(runnable);
        return runnable.getContent();
    }

    private class AppiumGetClipboardRunnable implements Runnable {
        private final ClipDataType contentType;
        private String content;

        AppiumGetClipboardRunnable(ClipDataType contentType) {
            this.contentType = contentType;
        }

        @Override
        public void run() {
            if (contentType != ClipDataType.PLAINTEXT) {
                throw new IllegalArgumentException();
            }
            content = toBase64String(new ClipboardHelper(mInstrumentation.getTargetContext()).getTextData());
        }

        public String getContent() {
            return content;
        }
    }
}
