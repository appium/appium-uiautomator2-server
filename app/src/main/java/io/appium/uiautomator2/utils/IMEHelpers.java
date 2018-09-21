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

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class IMEHelpers {
    private static final Map<String, Integer> ACTION_CODES_MAP = new HashMap<>();
    static {
        ACTION_CODES_MAP.put("normal", 0);
        ACTION_CODES_MAP.put("unspecified", 0);
        ACTION_CODES_MAP.put("none", 1);
        ACTION_CODES_MAP.put("go", 2);
        ACTION_CODES_MAP.put("search", 3);
        ACTION_CODES_MAP.put("send", 4);
        ACTION_CODES_MAP.put("next", 5);
        ACTION_CODES_MAP.put("done", 6);
        ACTION_CODES_MAP.put("previous", 7);
    }
    private final Instrumentation mInstrumentation = InstrumentationRegistry.getInstrumentation();

    public IMEHelpers() {
    }

    private InputConnection getInputConnection() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) mInstrumentation
                    .getTargetContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                Field servedInputConnectionWrapperField = InputMethodManager.class.getDeclaredField("mServedInputConnectionWrapper");
                servedInputConnectionWrapperField.setAccessible(true);
                Object servedInputConnectionWrapper = servedInputConnectionWrapperField.get(inputMethodManager);
                Method getInputConnection = servedInputConnectionWrapper.getClass().getMethod("getInputConnection");
                getInputConnection.setAccessible(true);
                return (InputConnection) getInputConnection.invoke(servedInputConnectionWrapper);
            } catch (NoSuchFieldException e) {
                Field servedInputConnectionField = InputMethodManager.class.getDeclaredField("mServedInputConnection");
                servedInputConnectionField.setAccessible(true);
                return (InputConnection) servedInputConnectionField.get(inputMethodManager);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean performEditorAction(String action) {
        final Integer editorAction = ACTION_CODES_MAP.get(action.toLowerCase());
        if (editorAction == null) {
            throw new IllegalArgumentException(
                    String.format("Only the following editor actions are supported: %s. " +
                            "'%s' is given instead", ACTION_CODES_MAP.keySet(), action));
        }

        final AtomicBoolean isSuccessful = new AtomicBoolean(true);
        mInstrumentation.runOnMainSync(
                new Runnable() {
                    @Override
                    public void run() {
                        isSuccessful.set(getInputConnection().performEditorAction(editorAction));
                    }
                }
        );
        return isSuccessful.get();
    }
}
