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

import android.app.UiAutomation;
import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;

import io.appium.uiautomator2.core.UiAutomatorBridge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UiAutomatorBridge.class, NotificationListener.class, UiAutomation.class})
@SuppressStaticInitializationFor({"io.appium.uiautomator2.core.UiAutomatorBridge"})
public class NotificationListenerTests {

    @Mock
    UiAutomatorBridge uiAutomatorBridge;

    @Mock
    UiAutomation uiAutomation;

    @Mock
    AccessibilityEvent accessibilityEvent;

    NotificationListener notificationListener;

    @Before
    public void setup() throws Exception {
        notificationListener = constructor(NotificationListener.class).newInstance();

        PowerMockito.mockStatic(UiAutomatorBridge.class);
        PowerMockito.mockStatic(NotificationListener.class);
        PowerMockito.suppress(constructor(UiAutomatorBridge.class));

        when(UiAutomatorBridge.getInstance()).thenReturn(uiAutomatorBridge);

        when(accessibilityEvent.getText()).thenReturn(new ArrayList<CharSequence>());

        when(uiAutomatorBridge.getUiAutomation()).thenReturn(uiAutomation);
        when(uiAutomation.executeAndWaitForEvent(
                (Runnable) any(), (UiAutomation.AccessibilityEventFilter) any(), anyLong()))
                .thenReturn(accessibilityEvent);
    }

    @Test
    public void shouldBeAbleToStartListener() {
        notificationListener.start();
        Thread listener = getListener();
        assertTrue(listener.isAlive());
    }

    @Test
    public void shouldBeAbleToStopListener() {
        notificationListener.start();
        notificationListener.stop();
        Thread listener = getListener();
        assertFalse(listener.isAlive());
    }

    @Test
    public void shouldDoNothingOnStopIfListenerIsNeverStartedYet() {
        Thread listener = getListener();
        notificationListener.stop();
        assertNull(listener);
    }

    @Test
    public void shouldDoNothingOnStopIfListenerIsAlreadyStopped() throws InterruptedException {
        notificationListener.start();
        notificationListener.stop();
        Thread listener = spy(getListener());
        notificationListener.stop();
        verify(listener, never()).join();
    }

    @Test
    public void shouldDoNothingOnStartIfListenerIsAlreadyStarted() {
        notificationListener.start();
        Thread listener = spy(getListener());
        notificationListener.start();
        verify(listener, never()).start();
    }

    @Test
    public void shouldBeAbleToRestartListener() {
        notificationListener.start();
        notificationListener.stop();
        notificationListener.start();
        Thread listener = getListener();
        assertTrue(listener.isAlive());
    }

    private Thread getListener() {
        return Thread.class.cast(Whitebox.getInternalState(notificationListener, "listener"));
    }
}
