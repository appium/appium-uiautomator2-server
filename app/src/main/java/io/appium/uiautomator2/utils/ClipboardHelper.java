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

package io.appium.uiautomator2.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.Arrays;

public class ClipboardHelper {
    private static final int DEFAULT_LABEL_LEN = 10;

    private final Context context;

    public ClipboardHelper(Context context) {
        this.context = context;
    }

    private ClipboardManager getManager() {
        final ClipboardManager cm = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            throw new ClipboardError("Cannot receive ClipboardManager instance from the system");
        }
        return cm;
    }

    public String getTextData() {
        final ClipboardManager cm = getManager();
        if (!cm.hasPrimaryClip()) {
            return "";
        }
        final ClipData cd = cm.getPrimaryClip();
        if (cd == null || cd.getItemCount() == 0) {
            return "";
        }
        final CharSequence text = cd.getItemAt(0).coerceToText(context);
        return text == null ? "" : text.toString();
    }

    public void setTextData(@Nullable String label, String data) {
        final ClipboardManager cm = getManager();
        if (label == null) {
            label = data.length() >= DEFAULT_LABEL_LEN
                    ? data.substring(0, DEFAULT_LABEL_LEN)
                    : data;
        }
        cm.setPrimaryClip(ClipData.newPlainText(label, data));
    }

    public enum ClipDataType {
        PLAINTEXT;

        public static String supportedDataTypes() {
            return Arrays.toString(values());
        }
    }

    public static class ClipboardError extends RuntimeException {
        ClipboardError(String message) {
            super(message);
        }
    }
}
