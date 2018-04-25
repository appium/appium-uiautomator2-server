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

package io.appium.uiautomator2.model.settings;

public enum Settings {
    ACTION_ACKNOWLEDGMENT_TIMEOUT(new ActionAcknowledgmentTimeout()),
    ALLOW_INVISIBLE_ELEMENTS(new AllowInvisibleElements()),
    COMPRESSED_LAYOUT_HIERARCHY(new CompressedLayoutHierarchy()),
    ELEMENT_RESPONSE_ATTRIBUTES(new ElementResponseAttributes()),
    ENABLE_NOTIFICATION_LISTENER(new EnableNotificationListener()),
    KEY_INJECTION_DELAY(new KeyInjectionDelay()),
    SCROLL_ACKNOWLEDGMENT_TIMEOUT(new ScrollAcknowledgmentTimeout()),
    SHOULD_USE_COMPACT_RESPONSES(new ShouldUseCompactResponses()),
    WAIT_FOR_IDLE_TIMEOUT(new WaitForIdleTimeout()),
    WAIT_FOR_SELECTOR_TIMEOUT(new WaitForSelectorTimeout()),
    SHUTDOWN_ON_POWER_DISCONNECT(new ShutdownOnPowerDisconnect());

    private final ISetting setting;

    Settings(ISetting setting) {
        this.setting = setting;
    }

    public ISetting getSetting() {
        return setting;
    }

    @Override
    public String toString() {
        return setting.getName();
    }
}
