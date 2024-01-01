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

import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import java.util.UUID;

public class BySelectorHelper {
    private static BySelector makeDummySelector() {
        return By.res(String.format("DUMMY:%s", UUID.randomUUID()));
    }

    @NonNull
    public static BySelector toBySelector(@Nullable AccessibilityNodeInfo node) {
        if (node == null) {
            return makeDummySelector();
        }

        BySelector result = null;
        // This might be simplified, but needs API 24+ with lambdas support
        CharSequence className = node.getClassName();
        if (className != null) {
            result = By.clazz(className.toString());
        }
        CharSequence description = node.getContentDescription();
        if (description != null) {
            result = result == null
                    ? By.desc(description.toString())
                    : result.desc(description.toString());
        }
        CharSequence pkg = node.getPackageName();
        if (pkg != null) {
            result = result == null ? By.pkg(pkg.toString()) : result.pkg(pkg.toString());
        }
        CharSequence res = node.getViewIdResourceName();
        if (res != null) {
            result = result == null ? By.res(res.toString()) : result.res(res.toString());
        }
        CharSequence text = node.getText();
        if (text != null) {
            result = result == null ? By.text(text.toString()) : result.text(text.toString());
        }

        result = result == null
                ? By.checkable(node.isCheckable())
                : result.checkable(node.isCheckable());
        result = result == null
                ? By.checked(node.isChecked())
                : result.checked(node.isChecked());
        result = result == null
                ? By.clickable(node.isClickable())
                : result.clickable(node.isClickable());
        result = result == null
                ? By.longClickable(node.isLongClickable())
                : result.longClickable(node.isLongClickable());
        result = result == null
                ? By.enabled(node.isEnabled())
                : result.enabled(node.isEnabled());
        result = result == null
                ? By.focusable(node.isFocusable())
                : result.focusable(node.isFocusable());
        result = result == null
                ? By.focused(node.isFocused())
                : result.focused(node.isFocused());
        result = result == null
                ? By.scrollable(node.isScrollable())
                : result.scrollable(node.isScrollable());
        result = result == null
                ? By.selected(node.isSelected())
                : result.selected(node.isSelected());

        return result == null ? makeDummySelector() : result;
    }
}
