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

package io.appium.uiautomator2.model.internal;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.os.Build;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Configurator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.ScreenRotation;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;
import io.appium.uiautomator2.utils.ReflectionUtils;

import static io.appium.uiautomator2.model.AccessibleUiObject.toAccessibleUiObject;
import static io.appium.uiautomator2.utils.AXWindowHelpers.getCachedWindowRoots;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ReflectionUtils.getConstructor;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.getMethod;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;

public class CustomUiDevice {
    private static final int CHANGE_ROTATION_TIMEOUT_MS = 2000;

    private static final String FIELD_M_INSTRUMENTATION = "mInstrumentation";

    private static CustomUiDevice INSTANCE = null;
    private final Method METHOD_FIND_MATCH;
    private final Method METHOD_FIND_MATCHES;
    private final Class<?> ByMatcherClass;
    private final Constructor<?> uiObject2Constructor;
    private final Instrumentation mInstrumentation;
    private GestureController gestureController;

    private CustomUiDevice() {
        this.mInstrumentation = (Instrumentation) getField(UiDevice.class, FIELD_M_INSTRUMENTATION, Device.getUiDevice());
        this.ByMatcherClass = ReflectionUtils.getClass("androidx.test.uiautomator.ByMatcher");
        this.METHOD_FIND_MATCH = getMethod(ByMatcherClass, "findMatch", UiDevice.class, BySelector.class, AccessibilityNodeInfo[].class);
        this.METHOD_FIND_MATCHES = getMethod(ByMatcherClass, "findMatches", UiDevice.class, BySelector.class, AccessibilityNodeInfo[].class);
        this.uiObject2Constructor = getConstructor(UiObject2.class, UiDevice.class, BySelector.class, AccessibilityNodeInfo.class);
    }

    public static synchronized CustomUiDevice getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomUiDevice();
        }
        return INSTANCE;
    }

    public Instrumentation getInstrumentation() {
        return mInstrumentation;
    }

    public UiAutomation getUiAutomation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int flags = Configurator.getInstance().getUiAutomationFlags();
            return getInstrumentation().getUiAutomation(flags);
        }
        return getInstrumentation().getUiAutomation();
    }

    private UiObject2 toUiObject2(@Nullable Object selector, @Nullable AccessibilityNodeInfo node) {
        if (selector == null) {
            // FIXME: The 'selector' should be proper By instance as non-null in the interaction.
            Logger.debug("FIXME: selector argument should not be null in androidx.test.uiautomator:uiautomator:2.3.0");
        }
        if (node == null && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            // TODO: remove this comment after upgrading to androidx.test.uiautomator:uiautomator:2.3.0
            // UiObject2 with androidx.test.uiautomator:uiautomator:2.3.0 has below code to crate the instance,
            // thus if the node was None, it should create an empty element for the AccessibilityNodeInfo.
            //    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //        AccessibilityWindowInfo window = UiObject2.Api21Impl.getWindow(cachedNode);
            //        mDisplayId = window == null ? Display.DEFAULT_DISPLAY : UiObject2.Api30Impl.getDisplayId(window);
            //    } else {
            //        mDisplayId = Display.DEFAULT_DISPLAY;
            //    }
            node = new AccessibilityNodeInfo();
        }
        Object[] constructorParams = {getUiDevice(), selector, node};
        try {
            return (UiObject2) uiObject2Constructor.newInstance(constructorParams);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            String msg = String.format("Cannot create UiObject2 instance with '%s' selector", selector);
            Logger.error(msg, e);
            throw new UiAutomator2Exception(msg, e);
        }
    }

    /**
     * Returns the first object to match the {@code selector} criteria.
     *
     * @throws InvalidSelectorException if given selector is unsupported/unknown
     */
    @Nullable
    public AccessibleUiObject findObject(Object selector) throws UiAutomator2Exception {
        final AccessibilityNodeInfo node;
        if (selector instanceof BySelector) {
            node = (AccessibilityNodeInfo) invoke(METHOD_FIND_MATCH, ByMatcherClass,
                    Device.getUiDevice(), selector, getCachedWindowRoots());
        } else if (selector instanceof NodeInfoList) {
            node = ((NodeInfoList) selector).getFirst();
            selector = toSelector(node);
        } else if (selector instanceof AccessibilityNodeInfo) {
            node = (AccessibilityNodeInfo) selector;
            selector = toSelector(node);
        } else if (selector instanceof UiSelector) {
            return toAccessibleUiObject(getUiDevice().findObject((UiSelector) selector));
        } else {
            throw new InvalidSelectorException("Selector of type " + selector.getClass().getName() + " not supported");
        }
        return node == null ? null : new AccessibleUiObject(toUiObject2(selector, node), node);
    }

    public synchronized GestureController getGestureController() {
        if (gestureController == null) {
            UiObject2 dummyElement = toUiObject2(null, null);
            Gestures gestures = new Gestures(getField("mGestures", dummyElement));
            gestureController = new GestureController(getField("mGestureController", dummyElement), gestures);
        }
        return gestureController;
    }

    /**
     * Returns List<object> to match the {@code selector} criteria.
     */
    public List<AccessibleUiObject> findObjects(Object selector) throws UiAutomator2Exception {
        List<AccessibleUiObject> ret = new ArrayList<>();

        final List<AccessibilityNodeInfo> axNodesList;
        if (selector instanceof BySelector) {
            //noinspection unchecked
            axNodesList = (List<AccessibilityNodeInfo>) invoke(
                    METHOD_FIND_MATCHES, ByMatcherClass, getUiDevice(), selector, getCachedWindowRoots());
        } else if (selector instanceof NodeInfoList) {
            axNodesList = ((NodeInfoList) selector).getAll();
        } else {
            throw new InvalidSelectorException("Selector of type " + selector.getClass().getName() + " not supported");
        }
        for (AccessibilityNodeInfo node : axNodesList) {
            UiObject2 uiObject2 = toUiObject2(toSelector(node), node);
            ret.add(new AccessibleUiObject(uiObject2, node));
        }

        return ret;
    }

    @Nullable
    private static BySelector toSelector(@Nullable AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return null;
        }
        final CharSequence className = nodeInfo.getClassName();
        return className == null ? null : By.clazz(className.toString());
    }

    public ScreenRotation setRotationSync(ScreenRotation desired) {
        if (ScreenRotation.current() == desired) {
            return desired;
        }

        getUiAutomation().setRotation(desired.ordinal());
        long start = System.currentTimeMillis();
        do {
            if (ScreenRotation.current() == desired) {
                return desired;
            }
            SystemClock.sleep(100);
        } while (System.currentTimeMillis() - start < CHANGE_ROTATION_TIMEOUT_MS);
        throw new InvalidElementStateException(String.format("Screen rotation cannot be changed to %s after %sms. " +
                "Is it locked programmatically?", desired.toString(), CHANGE_ROTATION_TIMEOUT_MS));
    }
}
