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

package io.appium.uiautomator2.server;

import android.content.Intent;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import io.appium.uiautomator2.server.ServerInstrumentation.PowerConnectionReceiver;

import static io.appium.uiautomator2.model.settings.Settings.SHUTDOWN_ON_POWER_DISCONNECT;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServerInstrumentationTests {
    private static final MockContext context = new MockContext();
    private static ServerInstrumentation serverInstrumentation;

    @SuppressWarnings("unchecked")
    @RunWith(PowerMockRunner.class)
    @PrepareForTest(ServerInstrumentation.class)
    public static class PowerConnectionReceiverTests {
        private final TestIntent intent = new TestIntent(Intent.ACTION_POWER_DISCONNECTED);
        private PowerConnectionReceiver powerConnectionReceiver;

        @Before
        public void setUp() {
            PowerMockito.mockStatic(ServerInstrumentation.class);
            SHUTDOWN_ON_POWER_DISCONNECT.getSetting().update(true);
            serverInstrumentation = spy(new ServerInstrumentation(context, 1025));

            when(ServerInstrumentation.getInstance()).thenReturn(serverInstrumentation);
            Whitebox.setInternalState(ServerInstrumentation.class, "instance",
                    serverInstrumentation);
            doNothing().when(serverInstrumentation).stopServer();
            powerConnectionReceiver = new PowerConnectionReceiver();
        }

        @Test
        public void shouldShutdownServerOnPowerDisconnected() {
            powerConnectionReceiver.onReceive(context, intent);
            verify(serverInstrumentation).stopServer();
        }

        @Test
        public void shouldSkipInvalidActions() {
            powerConnectionReceiver.onReceive(context, new TestIntent(Intent.
                    ACTION_POWER_CONNECTED));
            verify(serverInstrumentation, never()).stopServer();
        }

        @Test
        public void shouldNOTShutdownServerIfSettingValueIsFalse() {
            SHUTDOWN_ON_POWER_DISCONNECT.getSetting().update(false);
            powerConnectionReceiver.onReceive(context, intent);
            verify(serverInstrumentation, never()).stopServer();
        }

        @Test
        public void shouldNOTShutdownServerIfAlreadyDown() {
            Whitebox.setInternalState(ServerInstrumentation.class, "instance",
                    (ServerInstrumentation) null);
            powerConnectionReceiver.onReceive(context, intent);
            verify(serverInstrumentation, never()).stopServer();
        }
    }

    private static class TestIntent extends Intent {
        private final String action;

        public TestIntent(String action) {
            this.action = action;
        }

        @Override
        public String getAction() {
            return action;
        }
    }
}
