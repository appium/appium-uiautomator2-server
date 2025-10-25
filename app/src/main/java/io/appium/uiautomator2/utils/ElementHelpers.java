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

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import java.util.Arrays;
import java.util.Objects;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.NoSuchAttributeException;
import io.appium.uiautomator2.core.AxNodeInfoExtractor;
import io.appium.uiautomator2.core.AxNodeInfoHelper;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;

import static android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
import static io.appium.uiautomator2.model.internal.CustomUiDevice.getInstance;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToString;
import static io.appium.uiautomator2.utils.StringHelpers.toNonNullString;

public abstract class ElementHelpers {
    public static boolean canSetProgress(Object element) {
        AccessibilityNodeInfo nodeInfo = AxNodeInfoExtractor.toAxNodeInfo(element);
        return nodeInfo.getActionList().contains(ACTION_SET_PROGRESS);
    }

    /**
     * Set text of an element
     *
     * @param element - target element
     * @param text    - desired text
     * @return true if the text has been successfully set
     */
    public static boolean setText(final Object element, @Nullable final String text) {
        // Per framework convention, setText(null) means clearing it
        String textToSend = toNonNullString(text);
        AccessibilityNodeInfo nodeInfo = AxNodeInfoExtractor.toAxNodeInfo(element);


        textToSend = AxNodeInfoHelper.truncateTextToMaxLength(nodeInfo, textToSend);

        Logger.debug("Sending text to element: " + textToSend);
        Bundle args = new Bundle();
        args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, textToSend);
        return nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args);
    }

    public static void setProgress(final Object element, float value) {
        AccessibilityNodeInfo nodeInfo = AxNodeInfoExtractor.toAxNodeInfo(element);
        AxNodeInfoHelper.setProgressValue(nodeInfo, value);
    }

    public static AndroidElement findElement(final BySelector ui2BySelector) {
        AccessibleUiObject accessibleUiObject = getInstance().findObject(ui2BySelector);
        if (accessibleUiObject == null) {
            throw new ElementNotFoundException();
        }
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        return session.getElementsCache().add(accessibleUiObject, true);
    }

    public static NoSuchAttributeException generateNoAttributeException(@Nullable String attributeName) {
        return new NoSuchAttributeException(String.format("'%s' attribute is unknown for the element. " +
                        "Only the following attributes are supported: %s", attributeName,
                Arrays.toString(Attribute.exposableAliases())));
    }

    @NonNull
    public static String getText(Object element) {
        //noinspection ConstantConditions
        return getText(element, true);
    }

    @Nullable
    public static String getText(Object element, boolean replaceNull) {
        if (element instanceof UiObject2) {
            /*
             * If the given element is TOAST element, we can't perform any operation on {@link UiObject2} as it
             * not formed with valid AccessibilityNodeInfo, Instead we are using custom created AccessibilityNodeInfo of
             * TOAST Element to retrieve the Text.
             */
            AccessibilityNodeInfo nodeInfo = (AccessibilityNodeInfo) getField(UiObject2.class,
                    "mCachedNode", element);
            if (nodeInfo != null && Objects.equals(nodeInfo.getClassName(), Toast.class.getName())) {
                return charSequenceToString(nodeInfo.getText(), replaceNull);
            }
        }

        return AxNodeInfoHelper.getText(AxNodeInfoExtractor.toAxNodeInfo(element), replaceNull);
    }

}
