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

import android.os.Build;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.view.accessibility.AccessibilityNodeInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.core.AccessibilityNodeInfoHelper;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.utils.UnicodeEncoder;

import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public abstract class ElementHelpers {

    private static Method findAccessibilityNodeInfo;

    private static AccessibilityNodeInfo elementToNode(Object element) {
        AccessibilityNodeInfo result = null;
        try {
            result = (AccessibilityNodeInfo) findAccessibilityNodeInfo.invoke((UiObject) element, 5000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Remove all duplicate elements from the provided list
     *
     * @param elements - elements to remove duplicates from
     *
     * @return a new list with duplicates removed
     */
    public static List<Object> dedupe(List<Object> elements) {
        try {
            findAccessibilityNodeInfo = method(UiObject.class, "findAccessibilityNodeInfo", long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Object> result = new ArrayList<Object>();
        List<AccessibilityNodeInfo> nodes = new ArrayList<AccessibilityNodeInfo>();

        for (Object element : elements) {
            AccessibilityNodeInfo node = elementToNode(element);
            if (!nodes.contains(node)) {
                nodes.add(node);
                result.add(element);
            }
        }

        return result;
    }

    /**
     * Return the JSONObject which Appium returns for an element
     *
     * For example, appium returns elements like [{"ELEMENT":1}, {"ELEMENT":2}]
     */
    public static JSONObject toJSON(AndroidElement el) throws JSONException {
        return new JSONObject().put("ELEMENT", el.getId());
    }

    /**
     * Set text of an element
     *
     * @param element - target element
     * @param text - desired text
     * @param unicodeKeyboard - true, if text should be encoded to unicode
     */
    public static void setText(final Object element, final String text, final boolean unicodeKeyboard) throws UiObjectNotFoundException {
        String textToSend = text;
        AccessibilityNodeInfo nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);

        /*
         * Execute ACTION_SET_PROGRESS action (introduced in API level 24)
         * if element has range info and text can be converted to float.
         * Falling back to element.setText() if something goes wrong.
         */
        if (nodeInfo.getRangeInfo() != null && Build.VERSION.SDK_INT >= 24) {
            Logger.debug("Element has range info.");
            try {
                if (AccessibilityNodeInfoHelper.setProgressValue(nodeInfo, Float.parseFloat(text))) {
                    return;
                }
            } catch (NumberFormatException e) {
                Logger.debug(String.format("Can not convert \"%s\" to float.", text));
            }
            Logger.debug("Unable to perform ACTION_SET_PROGRESS action.  Falling back to element.setText()");
        }

        /*
         * Below Android 7.0 (API level 24) calling setText() throws
         * `IndexOutOfBoundsException: setSpan (x ... x) ends beyond length y`
         * if text length is greater than getMaxTextLength()
         */
        if (Build.VERSION.SDK_INT < 24) {
            textToSend = AccessibilityNodeInfoHelper.truncateTextToMaxLength(nodeInfo, textToSend);
        }

        if (unicodeKeyboard && UnicodeEncoder.needsEncoding(textToSend)) {
            Logger.debug("Sending Unicode text to element: " + textToSend);
            textToSend = UnicodeEncoder.encode(textToSend);
            Logger.debug("Encoded text: " + textToSend);
        }
        Logger.debug("Sending text to element: " + textToSend);
        if (element instanceof UiObject2) {
            UiObject2.class.cast(element).setText(textToSend);
        } else if (element instanceof UiObject) {
            UiObject.class.cast(element).setText(textToSend);
        } else {
            throw new UiAutomator2Exception("Unknown object type: " + element.getClass().getName());
        }
    }
}
