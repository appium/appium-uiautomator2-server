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

import android.util.SparseArray;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.core.AxNodeInfoExtractor;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelector;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelectors;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

public class ByUiAutomatorFinder {
    private static final String UI_SELECTOR_CRITERION_PREFIX = "SELECTOR_";

    public Object findOne(By.ByAndroidUiAutomator by)
            throws InvalidSelectorException, UiObjectNotFoundException {
        return findOne(by, null);
    }

    public Object findOne(By.ByAndroidUiAutomator by, @Nullable AndroidElement context)
            throws InvalidSelectorException, UiObjectNotFoundException {
        UiSelector selector = toSelector(by.getElementLocator());
        return context == null
                ? CustomUiDevice.getInstance().findObject(selector)
                : context.getChild(selector);
    }

    public List<Object> findMany(By.ByAndroidUiAutomator by)
            throws InvalidSelectorException, UiObjectNotFoundException {
        return findMany(by, null);
    }

    /**
     * returns  List<UiObject> using '-android automator' expression
     **/
    public List<Object> findMany(By.ByAndroidUiAutomator by, @Nullable AndroidElement context)
            throws InvalidSelectorException, UiObjectNotFoundException {
        List<Object> foundElements = new ArrayList<>();
        for (UiSelector sel : toSelectors(by.getElementLocator())) {
            // With multiple selectors, we expect that some elements may not exist.
            try {
                foundElements.addAll(fetchElements(sel, context));
            } catch (UiObjectNotFoundException ignored) {
                // for findElements up on no elements, empty array should return.
            }
        }
        return dedupe(foundElements);
    }

    private List<Object> fetchElements(UiSelector sel, @Nullable AndroidElement context)
            throws UiObjectNotFoundException, InvalidSelectorException {
        Logger.debug("getElements selector:" + sel.toString());
        final ArrayList<Object> elements = new ArrayList<>();

        // If sel is UiSelector[CLASS=android.widget.Button, INSTANCE=0]
        // then invoking instance with a non-0 argument will corrupt the selector.
        //
        // sel.instance(1) will transform the selector into:
        // UiSelector[CLASS=android.widget.Button, INSTANCE=1]
        //
        // The selector now points to an entirely different element.
        if (doesUiSelectorHaveAttribute(sel, "INSTANCE")) {
            Logger.debug("Selector has INSTANCE attribute");
            // There's exactly one element when using instance.
            UiObject instanceObj = getUiDevice().findObject(sel);
            if (instanceObj != null && instanceObj.exists()) {
                elements.add(instanceObj);
            }
            return elements;
        }

        UiObject lastFoundObj;
        UiSelector tmpSelector;
        int counter = 0;
        boolean keepSearching = true;
        final boolean useIndex = doesUiSelectorHaveAttribute(sel, "CLASS_REGEX");
        if (useIndex) {
            Logger.debug("Selector has CLASS_REGEX attribute");
        }
        while (keepSearching) {
            if (context == null) {
                tmpSelector = useIndex ? sel.index(counter) : sel.instance(counter);
                Logger.debug("getElements tmp selector:" + tmpSelector.toString());
                lastFoundObj = getUiDevice().findObject(tmpSelector);
            } else {
                lastFoundObj = (UiObject) context.getChild(sel.instance(counter));
            }
            counter++;
            if (lastFoundObj != null && lastFoundObj.exists()) {
                elements.add(lastFoundObj);
            } else {
                keepSearching = false;
            }
        }
        return elements;
    }

    /**
     * Remove all duplicate elements from the provided list
     *
     * @param elements - elements to remove duplicates from
     * @return a new list with duplicates removed
     */
    private static List<Object> dedupe(List<Object> elements) {
        List<Object> result = new ArrayList<>();
        Set<AccessibilityNodeInfo> nodes = new HashSet<>();
        for (Object element : elements) {
            AccessibilityNodeInfo node = AxNodeInfoExtractor.toNullableAxNodeInfo(element);
            if (node != null && !nodes.contains(node)) {
                nodes.add(node);
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Checks if UiSelector instance has the given attribute
     *
     * @param sel the selector to check
     * @param attributeName one of `SELECTOR_` constant names of UiSelector class
     * @return Either true or false
     */
    private static boolean doesUiSelectorHaveAttribute(UiSelector sel, String attributeName) {
        //noinspection rawtypes
        SparseArray selectorAttributes = (SparseArray) getField("mSelectorAttributes", sel);
        int expectedCriterion;
        try {
            String fieldName = attributeName.startsWith(UI_SELECTOR_CRITERION_PREFIX)
                    ? attributeName
                    : (UI_SELECTOR_CRITERION_PREFIX + attributeName.toUpperCase());
            Field field = UiSelector.class.getField(fieldName);
            field.setAccessible(true);
            expectedCriterion = field.getInt(sel);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (NoSuchFieldException e) {
            return false;
        }
        int criterionCount = selectorAttributes.size();
        for (int i = 0; i < criterionCount; i++) {
            int criterion = selectorAttributes.keyAt(i);
            if (criterion == expectedCriterion) {
                return true;
            }
        }
        return false;
    }
}
