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

/**
 * When enabled, the server clears the AccessibilityInteractionClient cache before a find/source
 * lookup only if a relevant UI-change AccessibilityEvent has been observed since the last reset
 * (rather than on every single lookup). This avoids forcing a full Binder re-traversal of the
 * accessibility tree per find, which drives unbounded ART LinearAlloc growth under sustained
 * lookups (see appium/appium-uiautomator2-server#774).
 *
 * Defaults to {@code false} to preserve the long-standing always-reset behavior. The optimization
 * also fails safe to always-reset whenever the accessibility event listener is not active (since
 * staleness cannot be detected without it).
 */
public class DeferAccessibilityCacheReset extends AbstractSetting<Boolean> {
    private static final String SETTING_NAME = "deferAccessibilityCacheReset";
    private static final Boolean DEFAULT_VALUE = false;
    private Boolean value = DEFAULT_VALUE;

    public DeferAccessibilityCacheReset() {
        super(Boolean.class, SETTING_NAME);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public Boolean getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    protected void apply(Boolean deferAccessibilityCacheReset) {
        value = deferAccessibilityCacheReset;
    }
}
