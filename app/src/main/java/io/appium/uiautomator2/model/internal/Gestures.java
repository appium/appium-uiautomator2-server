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

package io.appium.uiautomator2.model.internal;

import android.view.ViewConfiguration;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

public class Gestures {
    private final Object wrappedInstance;
    private final ViewConfiguration viewConfiguration;

    Gestures(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
        this.viewConfiguration = (ViewConfiguration) getField("mViewConfig", wrappedInstance);
    }

    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }
}
