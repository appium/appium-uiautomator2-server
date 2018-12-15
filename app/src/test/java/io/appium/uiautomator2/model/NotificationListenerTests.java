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

import android.app.UiAutomation.OnAccessibilityEventListener;
import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import io.appium.uiautomator2.core.UiAutomation;
import io.appium.uiautomator2.core.UiAutomatorBridge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NotificationListener.class, UiAutomation.class, UiAutomatorBridge.class,
        UiDevice.class, InstrumentationRegistry.class})
public class NotificationListenerTests {

    private NotificationListener notificationListener;
    private UiAutomation uiAutomation;
    private OnAccessibilityEventListener originalAccessibilityEventListener;
    private List<CharSequence> toastText;

    @Before
    public void setup() {
        toastText =  new ArrayList<>();
        toastText.add("toast text");

        originalAccessibilityEventListener = mock(OnAccessibilityEventListener.class);
        PowerMockito.mockStatic(InstrumentationRegistry.class);
        when(InstrumentationRegistry.getInstrumentation()).thenReturn(null);

        PowerMockito.mockStatic(UiDevice.class);
        when(UiDevice.getInstance(null)).thenReturn(mock(UiDevice.class));
        when(UiDevice.getInstance()).thenReturn(mock(UiDevice.class));

        PowerMockito.mockStatic(UiAutomatorBridge.class);
        when(UiAutomatorBridge.getInstance()).thenReturn(mock(UiAutomatorBridge.class));

        PowerMockito.mockStatic(UiAutomation.class);
        uiAutomation = mock(UiAutomation.class);
        when(UiAutomation.getInstance()).thenReturn(uiAutomation);
        when(uiAutomation.getOnAccessibilityEventListener())
                .thenReturn(originalAccessibilityEventListener);

        notificationListener = spy(new NotificationListener());
    }

    @Test
    public void shouldBeAbleToStartListener() {
        notificationListener.start();
        assertTrue(notificationListener.isListening());
    }

    @Test
    public void shouldBeAbleToStopListener() {
        notificationListener.start();
        notificationListener.stop();
        assertFalse(notificationListener.isListening());
    }

    @Test
    public void shouldDoNothingOnStopIfListenerIsAlreadyStopped() {
        doReturn(false).when(notificationListener).isListening();
        notificationListener.stop();
        verify(uiAutomation, never())
                .setOnAccessibilityEventListener((OnAccessibilityEventListener) any());
    }

    @Test
    public void shouldDoNothingOnStartIfListenerIsAlreadyStarted() {
        doReturn(true).when(notificationListener).isListening();
        notificationListener.start();
        verify(uiAutomation, never())
                .setOnAccessibilityEventListener((OnAccessibilityEventListener) any());
    }

    @Test
    public void shouldRestoreOriginalListener() {
        ArgumentCaptor<OnAccessibilityEventListener> argumentCaptor =
                ArgumentCaptor.forClass(OnAccessibilityEventListener.class);
        doNothing().when(uiAutomation).setOnAccessibilityEventListener(argumentCaptor.capture());
        doReturn(false).when(notificationListener).isListening();
        notificationListener.start();
        doReturn(true).when(notificationListener).isListening();
        notificationListener.stop();

        assertEquals(notificationListener, argumentCaptor.getAllValues().get(0));
        assertEquals(originalAccessibilityEventListener, argumentCaptor.getAllValues().get(1));
    }

    @Test
    public void shouldGrabAccessibilityEvent() {
        AccessibilityEvent accessibilityEvent = mock(AccessibilityEvent.class);
        when(accessibilityEvent.getEventType()).thenReturn(
                AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED);
        when(accessibilityEvent.getText()).thenReturn(toastText);

        notificationListener.onAccessibilityEvent(accessibilityEvent);
        assertEquals(toastText.get(0), notificationListener.getToastMessage().get(0));
    }

    @Test
    public void shouldClearToastMessageByTimeout() {
        notificationListener.setToastMessage(toastText);
        when(notificationListener.getToastClearTimeout()).thenReturn(-1L);

        assertTrue(notificationListener.getToastMessage().isEmpty());
    }

    @Test
    public void verifyClearTimeout() {
        assertEquals(3_500, notificationListener.getToastClearTimeout());
    }

    @Test
    public void shouldInvokeOriginalListener() {
        notificationListener.start();
        AccessibilityEvent accessibilityEvent = mock(AccessibilityEvent.class);
        notificationListener.onAccessibilityEvent(accessibilityEvent);

        verify(originalAccessibilityEventListener).onAccessibilityEvent(accessibilityEvent);
    }
}
