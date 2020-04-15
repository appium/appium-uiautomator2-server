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

import android.view.accessibility.AccessibilityEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.NonNull;
import io.appium.uiautomator2.core.UiAutomation;
import io.appium.uiautomator2.utils.Logger;

import static android.app.UiAutomation.OnAccessibilityEventListener;
import static java.lang.System.currentTimeMillis;

public class NotificationListener implements OnAccessibilityEventListener {
    private static NotificationListener INSTANCE;
    private static final int TOAST_CLEAR_TIMEOUT = 3500;

    private final UiAutomation uiAutomation;
    private final List<CharSequence> toastMessage = new CopyOnWriteArrayList<>();
    private long recentToastTimestamp = currentTimeMillis();
    private OnAccessibilityEventListener originalListener = null;
    private volatile boolean isListening;

    protected NotificationListener() {
        uiAutomation = UiAutomation.getInstance();
    }

    public static NotificationListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotificationListener();
        }
        return INSTANCE;
    }

    /**
     * Listens for Notification Messages
     */
    public void start() {
        if (isListening()) {
            Logger.debug("Toast notification listener is already started.");
            return;
        }
        Logger.debug("Starting toast notification listener.");
        originalListener = uiAutomation.getOnAccessibilityEventListener();
        isListening = true;
        Logger.debug("Original listener: " + originalListener);
        uiAutomation.setOnAccessibilityEventListener(this);
    }

    public void stop() {
        if (!isListening()) {
            Logger.debug("Toast notification listener is already stopped.");
            return;
        }
        Logger.debug("Stopping toast notification listener.");
        isListening = false;
        uiAutomation.setOnAccessibilityEventListener(originalListener);
    }

    @Override
    public synchronized void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            Logger.debug("Catch toast message: " + event);
            List<CharSequence> text = event.getText();
            if (text != null && !text.isEmpty()) {
                setToastMessage(text);
            }
        }

        if (originalListener != null) {
            originalListener.onAccessibilityEvent(event);
        }
    }

    public boolean isListening() {
        return isListening;
    }

    protected long getToastClearTimeout() {
        return TOAST_CLEAR_TIMEOUT;
    }

    @NonNull
    public List<CharSequence> getToastMessage() {
        if (!toastMessage.isEmpty() && currentTimeMillis() - recentToastTimestamp > getToastClearTimeout()) {
            Logger.debug("Clearing toast message: " + toastMessage);
            toastMessage.clear();
        }
        return toastMessage;
    }

    protected void setToastMessage(@NonNull List<CharSequence> text) {
        toastMessage.clear();
        toastMessage.addAll(text);
        recentToastTimestamp = currentTimeMillis();
    }
}
