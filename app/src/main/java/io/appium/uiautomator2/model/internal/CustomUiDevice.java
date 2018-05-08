package io.appium.uiautomator2.model.internal;

import android.app.Instrumentation;
import android.os.Build;
import android.os.SystemClock;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;
import io.appium.uiautomator2.utils.ReflectionUtils;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public class CustomUiDevice {

    private static final String FIELD_M_INSTRUMENTATION = "mInstrumentation";
    private static final String FIELD_API_LEVEL_ACTUAL = "API_LEVEL_ACTUAL";
    private static final boolean MULTI_WINDOW = false;

    private static CustomUiDevice INSTANCE = new CustomUiDevice();
    private final Method METHOD_FIND_MATCH;
    private final Method METHOD_FIND_MATCHS;
    private final Class ByMatcher;
    private final Instrumentation mInstrumentation;
    private final Object API_LEVEL_ACTUAL;

    /**
     * UiDevice in android open source project will Support multi-window searches for API level 21,
     * which has not been implemented in UiAutomatorViewer capture layout hierarchy, to be in sync
     * with UiAutomatorViewer customizing getWindowRoots() method to skip the multi-window search
     * based user passed property
     */
    public CustomUiDevice() {
        try {

            this.mInstrumentation = (Instrumentation) getField(UiDevice.class, FIELD_M_INSTRUMENTATION, Device.getUiDevice());
            this.API_LEVEL_ACTUAL = getField(UiDevice.class, FIELD_API_LEVEL_ACTUAL, Device.getUiDevice());
            METHOD_FIND_MATCH = method("android.support.test.uiautomator.ByMatcher", "findMatch", UiDevice.class, BySelector.class, AccessibilityNodeInfo[].class);
            METHOD_FIND_MATCHS = method("android.support.test.uiautomator.ByMatcher", "findMatches", UiDevice.class, BySelector.class, AccessibilityNodeInfo[].class);

            ByMatcher = ReflectionUtils.getClass("android.support.test.uiautomator" + ".ByMatcher");
        } catch (Error error) {
            Logger.error("ERROR", "error", error);
            throw error;
        } catch (UiAutomator2Exception error) {
            Logger.error("ERROR", "error", error);
            throw new Error(error);
        }
    }

    public static CustomUiDevice getInstance() {
        return INSTANCE;
    }

    public Instrumentation getInstrumentation() {
        return mInstrumentation;
    }

    /**
     * Returns the first object to match the {@code selector} criteria.
     */
    public Object findObject(Object selector) throws ClassNotFoundException, UiAutomator2Exception {

        AccessibilityNodeInfo node;
        Device.waitForIdle();
        if (selector instanceof BySelector) {
            node = (AccessibilityNodeInfo) invoke(METHOD_FIND_MATCH, ByMatcher, Device.getUiDevice(), selector, getWindowRoots());
        } else if (selector instanceof NodeInfoList) {
            node = ((NodeInfoList) selector).getNodeList().size() > 0 ? ((NodeInfoList) selector).getNodeList().get(0) : null;
            selector = By.clazz(node.getClassName().toString());
        } else if (selector instanceof AccessibilityNodeInfo) {
            node = (AccessibilityNodeInfo) selector;
            selector = By.clazz(node.getClassName().toString());
        } else if (selector instanceof UiSelector) {
            UiObject uiObject = getUiDevice().findObject((UiSelector) selector);
            if (uiObject.exists()) {
                return uiObject;
            }
            return null;
        } else {
            throw new InvalidSelectorException("Selector of type " + selector.getClass().getName() + " not supported");
        }
        try {
            if (node == null) {
                return null;
            }
            Class uiObject2 = Class.forName("android.support.test.uiautomator.UiObject2");
            Constructor cons = uiObject2.getDeclaredConstructors()[0];
            cons.setAccessible(true);
            Object[] constructorParams = {getUiDevice(), selector, node};

            final long timeoutMillis = 1000;
            long end = SystemClock.uptimeMillis() + timeoutMillis;
            while (true) {
                UiObject2 object2 = (UiObject2) cons.newInstance(constructorParams);

                if (object2 != null) {
                    return object2;
                }
                long remainingMillis = end - SystemClock.uptimeMillis();
                if (remainingMillis < 0) {
                    return null;
                }
                SystemClock.sleep(Math.min(200, remainingMillis));
            }

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            final String msg = String.format("error while creating  UiObject2 object");
            Logger.error(msg + " " + e);
            throw new UiAutomator2Exception(msg, e);
        }
    }

    /**
     * Returns List<object> to match the {@code selector} criteria.
     */
    public List<Object> findObjects(Object selector) throws ClassNotFoundException, UiAutomator2Exception {
        List<Object> ret = new ArrayList<>();

        ArrayList<AccessibilityNodeInfo> list;
        if (selector instanceof BySelector) {
            ReflectionUtils.getClass("android.support.test.uiautomator.ByMatcher");
            Object nodes = invoke(METHOD_FIND_MATCHS, ByMatcher, getUiDevice(), selector, getWindowRoots());
            list = (ArrayList) nodes;
        } else if (selector instanceof NodeInfoList) {
            list = ((NodeInfoList) selector).getNodeList();
        } else {
            throw new InvalidSelectorException("Selector of type " + selector.getClass().getName() + " not supported");
        }
        for (AccessibilityNodeInfo node : list) {
            try {
                Class uiObject2 = Class.forName("android.support.test.uiautomator" + ".UiObject2");
                Constructor cons = uiObject2.getDeclaredConstructors()[0];
                cons.setAccessible(true);
                selector = By.clazz(node.getClassName().toString());
                Object[] constructorParams = {getUiDevice(), selector, node};
                ret.add(cons.newInstance(constructorParams));
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                final String msg = "error while creating  UiObject2 object";
                Logger.error(msg + " " + e);
                throw new UiAutomator2Exception(msg, e);
            }
        }

        return ret;
    }

    /**
     * Returns a list containing the root {@link AccessibilityNodeInfo}s for each active window
     */
    private AccessibilityNodeInfo[] getWindowRoots() throws UiAutomator2Exception {
        Device.waitForIdle();
        ArrayList<AccessibilityNodeInfo> ret = new ArrayList<>();
        /*
         * TODO: MULTI_WINDOW is disabled, UIAutomatorViewer captures active window properties and
         * end users always relay on UIAutomatorViewer while writing tests.
         * If we enable MULTI_WINDOW it effects end users.
         * https://code.google.com/p/android/issues/detail?id=207569
         */
        if ((Integer) API_LEVEL_ACTUAL >= Build.VERSION_CODES.LOLLIPOP && MULTI_WINDOW) {
            // Support multi-window searches for API level 21 and up
            for (AccessibilityWindowInfo window : mInstrumentation.getUiAutomation().getWindows()) {
                AccessibilityNodeInfo root = window.getRoot();

                if (root == null) {
                    Logger.debug(String.format("Skipping null root node for " + "window: %s", window.toString()));
                    continue;
                }
                ret.add(root);
            }
            // Prior to API level 21 we can only access the active window
        } else {
            AccessibilityNodeInfo node = mInstrumentation.getUiAutomation().getRootInActiveWindow();
            if (node != null) {
                ret.add(node);
            } else {
                /*TODO: As we can't proceed to find element with out root node,
                 TODO: retrying for 5 times to get the root node if UiTestAutomationBridge reruns null
                 TODO: need to handle gracefully*/
                //AccessibilityNodeInfo should not be null.
                int retryCount = 0;
                while (node == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                    Device.waitForIdle();
                    Logger.debug(" ERROR: null root node returned by UiTestAutomationBridge, retrying: " + retryCount);
                    node = mInstrumentation.getUiAutomation().getRootInActiveWindow();
                    retryCount++;
                    if (node != null) {
                        ret.add(node);
                        break;
                    } else if (retryCount > 5) {
                        throw new UiAutomator2Exception("Unable to get Root in Active window," +
                                " ERROR: null root node returned by UiTestAutomationBridge.");
                    }
                }
            }
        }
        return ret.toArray(new AccessibilityNodeInfo[ret.size()]);
    }
}
