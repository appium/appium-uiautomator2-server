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
import android.os.SystemClock;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiSelector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.ScreenOrientation;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;
import io.appium.uiautomator2.utils.ReflectionUtils;

import static io.appium.uiautomator2.utils.AXWindowHelpers.getCachedWindowRoots;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ReflectionUtils.getConstructor;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.getMethod;

public class CustomUiDevice {
    private static final int CHANGE_ORIENTATION_TIMEOUT_MS = 2000;

    private static final String FIELD_M_INSTRUMENTATION = "mInstrumentation";
    private static final String FIELD_API_LEVEL_ACTUAL = "API_LEVEL_ACTUAL";
    private static final long UIOBJECT2_CREATION_TIMEOUT = 1000; // ms

    private static CustomUiDevice INSTANCE = null;
    private final Method METHOD_FIND_MATCH;
    private final Method METHOD_FIND_MATCHES;
    private final Class<?> ByMatcherClass;
    private final Constructor<?> uiObject2Constructor;
    private final Instrumentation mInstrumentation;
    private final Object API_LEVEL_ACTUAL;
    private GestureController gestureController;

    private CustomUiDevice() {
        this.mInstrumentation = (Instrumentation) getField(UiDevice.class, FIELD_M_INSTRUMENTATION, Device.getUiDevice());
        this.API_LEVEL_ACTUAL = getField(UiDevice.class, FIELD_API_LEVEL_ACTUAL, Device.getUiDevice());
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

    public int getApiLevelActual() {
        return (Integer) API_LEVEL_ACTUAL;
    }

    @Nullable
    private UiObject2 toUiObject2(Object selector, AccessibilityNodeInfo node) {
        Object[] constructorParams = {getUiDevice(), selector, node};
        long end = SystemClock.uptimeMillis() + UIOBJECT2_CREATION_TIMEOUT;
        while (true) {
            Object object2;
            try {
                object2 = uiObject2Constructor.newInstance(constructorParams);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                String msg = String.format("Cannot create UiObject2 instance with '%s' selector", selector);
                Logger.error(msg, e);
                throw new UiAutomator2Exception(msg, e);
            }
            if (object2 instanceof UiObject2) {
                return (UiObject2) object2;
            }
            long remainingMillis = end - SystemClock.uptimeMillis();
            if (remainingMillis < 0) {
                return null;
            }
            SystemClock.sleep(Math.min(200, remainingMillis));
        }
    }

    /**
     * Returns the first object to match the {@code selector} criteria.
     *
     * @throws InvalidSelectorException if given selector is unsupported/unknown
     */
    @Nullable
    public Object findObject(Object selector) throws UiAutomator2Exception {
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
            UiObject uiObject = getUiDevice().findObject((UiSelector) selector);
            return uiObject.exists() ? uiObject : null;
        } else {
            throw new InvalidSelectorException("Selector of type " + selector.getClass().getName() + " not supported");
        }
        return node == null ? null : toUiObject2(selector, node);
    }

    public synchronized GestureController getGestureController() {
        if (gestureController == null) {
            UiObject2 dummyElement = toUiObject2(null, null);
            if (dummyElement == null) {
                throw new IllegalStateException("Cannot create dummy UiObject2 instance");
            }
            Gestures gestures = new Gestures(getField("mGestures", dummyElement));
            gestureController = new GestureController(getField("mGestureController", dummyElement), gestures);
        }
        return gestureController;
    }

    /**
     * Returns List<object> to match the {@code selector} criteria.
     */
    public List<Object> findObjects(Object selector) throws UiAutomator2Exception {
        List<Object> ret = new ArrayList<>();

        List<AccessibilityNodeInfo> axNodesList;
        if (selector instanceof BySelector) {
            Object nodes = invoke(METHOD_FIND_MATCHES, ByMatcherClass, getUiDevice(), selector, getCachedWindowRoots());
            //noinspection unchecked
            axNodesList = (List) nodes;
        } else if (selector instanceof NodeInfoList) {
            axNodesList = ((NodeInfoList) selector).getAll();
        } else {
            throw new InvalidSelectorException("Selector of type " + selector.getClass().getName() + " not supported");
        }
        for (AccessibilityNodeInfo node : axNodesList) {
            UiObject2 uiObject2 = toUiObject2(toSelector(node), node);
            if (uiObject2 != null) {
                ret.add(uiObject2);
            }
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

    public ScreenOrientation setOrientationSync(ScreenOrientation desired) {
        if (ScreenOrientation.current() == desired) {
            return desired;
        }

        getInstrumentation().getUiAutomation().setRotation(desired.ordinal());
        long start = System.currentTimeMillis();
        do {
            if (ScreenOrientation.current() == desired) {
                return desired;
            }
            SystemClock.sleep(100);
        } while (System.currentTimeMillis() - start < CHANGE_ORIENTATION_TIMEOUT_MS);
        throw new InvalidElementStateException(String.format("Screen orientation cannot be changed to %s after %sms. " +
                "Is it locked programmatically?", desired.toString(), CHANGE_ORIENTATION_TIMEOUT_MS));
    }
}
