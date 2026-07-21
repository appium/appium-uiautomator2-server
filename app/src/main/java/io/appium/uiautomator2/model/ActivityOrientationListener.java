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

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.Nullable;

import io.appium.uiautomator2.core.UiAutomation;
import io.appium.uiautomator2.utils.Logger;

import static android.app.UiAutomation.OnAccessibilityEventListener;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

/**
 * Tracks the foreground activity from {@link AccessibilityEvent#TYPE_WINDOW_STATE_CHANGED} and
 * resolves its manifest-declared {@link ActivityInfo#screenOrientation}. Runtime
 * {@link android.app.Activity#setRequestedOrientation} overrides are not visible via this API.
 */
public class ActivityOrientationListener implements OnAccessibilityEventListener {
    private static ActivityOrientationListener INSTANCE;

    private final UiAutomation uiAutomation;
    private OnAccessibilityEventListener originalListener = null;
    private volatile boolean isListening;
    @Nullable
    private volatile ComponentName currentComponent;

    protected ActivityOrientationListener() {
        uiAutomation = UiAutomation.getInstance();
    }

    public static ActivityOrientationListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ActivityOrientationListener();
        }
        return INSTANCE;
    }

    public void start() {
        if (isListening()) {
            Logger.debug("Activity orientation listener is already started.");
            return;
        }
        Logger.debug("Starting activity orientation listener.");
        originalListener = uiAutomation.getOnAccessibilityEventListener();
        isListening = true;
        Logger.debug("Original listener: " + originalListener);
        uiAutomation.setOnAccessibilityEventListener(this);
    }

    public void stop() {
        if (!isListening()) {
            Logger.debug("Activity orientation listener is already stopped.");
            return;
        }
        Logger.debug("Stopping activity orientation listener.");
        isListening = false;
        uiAutomation.setOnAccessibilityEventListener(originalListener);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            CharSequence packageName = event.getPackageName();
            CharSequence className = event.getClassName();
            if (packageName != null && className != null) {
                currentComponent = new ComponentName(packageName.toString(), className.toString());
            }
        }

        if (originalListener != null) {
            originalListener.onAccessibilityEvent(event);
        }
    }

    /**
     * Returns the manifest-declared screen orientation constant name (e.g.
     * {@code SCREEN_ORIENTATION_PORTRAIT}), or {@code null} if unknown.
     */
    @Nullable
    public String currentScreenOrientationConstant() {
        ComponentName component = currentComponent;
        if (component == null) {
            return null;
        }
        try {
            Context context = getInstrumentation().getTargetContext();
            int screenOrientation = context.getPackageManager()
                    .getActivityInfo(component, 0).screenOrientation;
            return screenOrientationConstantName(screenOrientation);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Nullable
    public static String screenOrientationConstantName(int value) {
        switch (value) {
            case ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED:
                return "SCREEN_ORIENTATION_UNSPECIFIED";
            case ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                return "SCREEN_ORIENTATION_LANDSCAPE";
            case ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                return "SCREEN_ORIENTATION_PORTRAIT";
            case ActivityInfo.SCREEN_ORIENTATION_USER:
                return "SCREEN_ORIENTATION_USER";
            case ActivityInfo.SCREEN_ORIENTATION_BEHIND:
                return "SCREEN_ORIENTATION_BEHIND";
            case ActivityInfo.SCREEN_ORIENTATION_SENSOR:
                return "SCREEN_ORIENTATION_SENSOR";
            case ActivityInfo.SCREEN_ORIENTATION_NOSENSOR:
                return "SCREEN_ORIENTATION_NOSENSOR";
            case ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE:
                return "SCREEN_ORIENTATION_SENSOR_LANDSCAPE";
            case ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT:
                return "SCREEN_ORIENTATION_SENSOR_PORTRAIT";
            case ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE:
                return "SCREEN_ORIENTATION_REVERSE_LANDSCAPE";
            case ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT:
                return "SCREEN_ORIENTATION_REVERSE_PORTRAIT";
            case ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR:
                return "SCREEN_ORIENTATION_FULL_SENSOR";
            case ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE:
                return "SCREEN_ORIENTATION_USER_LANDSCAPE";
            case ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT:
                return "SCREEN_ORIENTATION_USER_PORTRAIT";
            case ActivityInfo.SCREEN_ORIENTATION_LOCKED:
                return "SCREEN_ORIENTATION_LOCKED";
            case ActivityInfo.SCREEN_ORIENTATION_FULL_USER:
                return "SCREEN_ORIENTATION_FULL_USER";
            default:
                return null;
        }
    }

    public boolean isListening() {
        return isListening;
    }
}
