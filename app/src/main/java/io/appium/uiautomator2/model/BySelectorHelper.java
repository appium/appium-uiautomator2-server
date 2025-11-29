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

package io.appium.uiautomator2.model;

import android.os.Build;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import java.util.UUID;

import io.appium.uiautomator2.core.UiAutomatorBridge;

public class BySelectorHelper {
    @NonNull
    public static BySelector toBySelector(@Nullable AccessibilityNodeInfo node) {
        if (node == null) {
            return makeDummySelector();
        }

        BySelector result = null;
        // The below conditions might be simplified, but need API 24+ with lambdas support
        CharSequence className = node.getClassName();
        if (hasValue(className)) {
            result = By.clazz(className.toString());
        }
        CharSequence desc = node.getContentDescription();
        if (hasValue(desc)) {
            result = result == null ? By.desc(desc.toString()) : result.desc(desc.toString());
        }
        CharSequence pkg = node.getPackageName();
        if (hasValue(pkg)) {
            result = result == null ? By.pkg(pkg.toString()) : result.pkg(pkg.toString());
        }
        CharSequence res = node.getViewIdResourceName();
        if (hasValue(res)) {
            result = result == null ? By.res(res.toString()) : result.res(res.toString());
        }
        CharSequence text = node.getText();
        if (hasValue(text)) {
            result = result == null ? By.text(text.toString()) : result.text(text.toString());
        }

        result = result == null
                ? By.checkable(node.isCheckable())
                : result.checkable(node.isCheckable());
        result = result.clickable(node.isClickable())
                .longClickable(node.isLongClickable())
                .focusable(node.isFocusable())
                .scrollable(node.isScrollable());
        return applyCurrentDisplay(result);
    }

    public static BySelector applyCurrentDisplay(BySelector selector) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
                && !UiAutomatorBridge.getInstance().isCurrentDisplayDefault()) {
            Display display = UiAutomatorBridge.getInstance().getCurrentDisplay();
            if (display != null) {
                return selector.displayId(display.getDisplayId());
            }
        }
        return selector;
    }

    private static boolean hasValue(@Nullable CharSequence cs) {
        return cs != null && cs.length() > 0;
    }

    private static BySelector makeDummySelector() {
        return By.text(String.format("DUMMY:%s", UUID.randomUUID()));
    }
}
