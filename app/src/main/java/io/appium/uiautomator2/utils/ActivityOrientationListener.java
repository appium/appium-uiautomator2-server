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

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.core.UiAutomation;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;

import static android.app.UiAutomation.OnAccessibilityEventListener;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

/**
 * Tracks the foreground activity from {@link AccessibilityEvent#TYPE_WINDOW_STATE_CHANGED} and
 * resolves its manifest-declared {@link ActivityInfo#screenOrientation}. Runtime
 * {@link android.app.Activity#setRequestedOrientation} overrides are not visible via this API.
 */
public class ActivityOrientationListener implements OnAccessibilityEventListener {
    private static ActivityOrientationListener INSTANCE;

    private final UiAutomation uiAutomation;
    private final Object guard = new Object();
    private OnAccessibilityEventListener originalListener = null;
    private boolean isListening;
    @Nullable
    private ComponentName currentComponent;

    protected ActivityOrientationListener() {
        uiAutomation = UiAutomation.getInstance();
    }

    public static synchronized ActivityOrientationListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ActivityOrientationListener();
        }
        return INSTANCE;
    }

    public void start() {
        synchronized (guard) {
            if (isListening) {
                Logger.debug("Activity orientation listener is already started.");
                return;
            }
            Logger.debug("Starting activity orientation listener.");
            originalListener = uiAutomation.getOnAccessibilityEventListener();
            isListening = true;
            seedInitialComponentFromSessionCaps();
            Logger.debug("Original listener: " + originalListener);
            uiAutomation.setOnAccessibilityEventListener(this);
        }
    }

    public void stop() {
        synchronized (guard) {
            if (!isListening) {
                Logger.debug("Activity orientation listener is already stopped.");
                return;
            }
            Logger.debug("Stopping activity orientation listener.");
            isListening = false;
            currentComponent = null;
            OnAccessibilityEventListener toRestore = originalListener;
            originalListener = null;
            uiAutomation.setOnAccessibilityEventListener(toRestore);
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        OnAccessibilityEventListener delegate;
        synchronized (guard) {
            if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                CharSequence packageName = event.getPackageName();
                CharSequence className = event.getClassName();
                if (packageName != null && className != null) {
                    currentComponent = new ComponentName(packageName.toString(), className.toString());
                }
            }
            delegate = originalListener;
        }

        if (delegate != null) {
            delegate.onAccessibilityEvent(event);
        }
    }

    /**
     * Returns the manifest-declared screen orientation constant name (e.g.
     * {@code SCREEN_ORIENTATION_PORTRAIT}), or {@code null} if unknown.
     */
    @Nullable
    public String currentScreenOrientationConstant() {
        ComponentName component;
        synchronized (guard) {
            component = currentComponent;
        }
        if (component == null) {
            return null;
        }
        try {
            int screenOrientation = getApplicationContext().getPackageManager()
                    .getActivityInfo(component, 0).screenOrientation;
            return screenOrientationConstantName(screenOrientation);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Nullable
    public static String screenOrientationConstantName(int value) {
        ScreenOrientationConstant constant = ScreenOrientationConstant.fromValue(value);
        return constant == null ? null : constant.constantName();
    }

    public boolean isListening() {
        synchronized (guard) {
            return isListening;
        }
    }

    private void seedInitialComponentFromSessionCaps() {
        Session session = AppiumUIA2Driver.getInstance().getSession();
        if (session == null) {
            return;
        }
        String appPackage = session.getCapability("appPackage", "");
        String appActivity = session.getCapability("appActivity", "");
        if (isBlank(appPackage) || isBlank(appActivity)) {
            return;
        }
        currentComponent = new ComponentName(appPackage, appActivity);
    }

    private enum ScreenOrientationConstant {
        UNSPECIFIED(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED),
        LANDSCAPE(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE),
        PORTRAIT(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT),
        USER(ActivityInfo.SCREEN_ORIENTATION_USER),
        BEHIND(ActivityInfo.SCREEN_ORIENTATION_BEHIND),
        SENSOR(ActivityInfo.SCREEN_ORIENTATION_SENSOR),
        NOSENSOR(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR),
        SENSOR_LANDSCAPE(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE),
        SENSOR_PORTRAIT(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT),
        REVERSE_LANDSCAPE(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE),
        REVERSE_PORTRAIT(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT),
        FULL_SENSOR(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR),
        USER_LANDSCAPE(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE),
        USER_PORTRAIT(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT),
        LOCKED(ActivityInfo.SCREEN_ORIENTATION_LOCKED),
        FULL_USER(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);

        private static final Map<Integer, ScreenOrientationConstant> BY_VALUE = new HashMap<>();

        static {
            for (ScreenOrientationConstant constant : values()) {
                BY_VALUE.put(constant.value, constant);
            }
        }

        private final int value;

        ScreenOrientationConstant(int value) {
            this.value = value;
        }

        @Nullable
        static ScreenOrientationConstant fromValue(int value) {
            return BY_VALUE.get(value);
        }

        String constantName() {
            return "SCREEN_ORIENTATION_" + name();
        }
    }
}
