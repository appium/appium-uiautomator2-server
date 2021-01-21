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

import androidx.annotation.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.Settings;

public class Session {
    public static final String NO_ID = "None";
    public static final int MAX_CACHE_SIZE = 500;

    private final Map<String, Object> capabilities = new HashMap<>();
    private final String sessionId;
    private final ElementsCache elementsCache = new ElementsCache(MAX_CACHE_SIZE);
    private AccessibilityScrollData lastScrollData;

    Session(String sessionId, Map<String, Object> capabilities) {
        this.sessionId = sessionId;
        for (Map.Entry<String, Object> capability: capabilities.entrySet()) {
            boolean isSetting = false;
            for (Settings settingsEnumItem: Settings.values()) {
                ISetting<?> currentSetting = settingsEnumItem.getSetting();
                if (currentSetting.getName().equalsIgnoreCase(capability.getKey())) {
                    isSetting = true;
                    currentSetting.update(capability.getValue());
                    break;
                }
            }
            if (!isSetting) {
                setCapability(capability.getKey(), capability.getValue());
            }
        }
    }

    private void setCapability(String name, Object value) {
        capabilities.put(name, value);
    }

    public <T> T getCapability(String name, T defaultValue) {
        //noinspection unchecked
        return hasCapability(name) ? (T) capabilities.get(name) : defaultValue;
    }

    public Map<String, Object> getCapabilities() {
        return Collections.unmodifiableMap(capabilities);
    }

    public boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }

    public String getSessionId() {
        return sessionId;
    }

    @Nullable
    public AccessibilityScrollData getLastScrollData() {
        return lastScrollData;
    }

    public void setLastScrollData(AccessibilityScrollData scrollData) {
        lastScrollData = scrollData;
    }

    public ElementsCache getElementsCache() {
        return this.elementsCache;
    }
}
