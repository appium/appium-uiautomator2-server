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
 * If enabled, Jetpack Compose's {@code testTag} semantics property (exposed in
 * {@link android.view.accessibility.AccessibilityNodeInfo#getExtras()} under the key
 * {@code androidx.compose.ui.semantics.testTag}) is used as the value of the
 * {@code resource-id} attribute for any node that carries it, taking precedence over the
 * node's real {@code resource-id} if it has one. This mirrors the exact behavior of Compose's
 * own {@code testTagsAsResourceId} semantics property, which unconditionally overwrites
 * {@code viewIdResourceName} with the {@code testTag} rather than only filling in a missing
 * one. It exists because {@code testTagsAsResourceId} can only be set from within the
 * application's own composable tree and cannot be toggled externally.
 */
public class MapTestTagToResourceId extends AbstractSetting<Boolean> {
    private static final String SETTING_NAME = "mapTestTagToResourceId";
    private static final boolean DEFAULT_VALUE = false;
    private boolean mapTestTagToResourceId = DEFAULT_VALUE;

    public MapTestTagToResourceId() {
        super(Boolean.class, SETTING_NAME);
    }

    @Override
    public Boolean getValue() {
        return mapTestTagToResourceId;
    }

    @Override
    public Boolean getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    protected void apply(Boolean value) {
        this.mapTestTagToResourceId = value;
    }
}
