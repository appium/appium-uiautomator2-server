/*
 * Copyright (C) 2013 DroidDriver committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.model;

import io.appium.uiautomator2.utils.NodeInfoList;

/**
 * Interface for finding UiElement.
 */
public interface Finder {
    /**
     * @param context The starting UiAutomationElement, used as search context
     * @return The matching elements on the current context
     */
    NodeInfoList find(UiElement context);

    /**
     * {@inheritDoc}
     * <p>
     * <p> It is recommended that this method return the description of the finder, for example,
     * "{text=OK}".
     */
    @Override
    String toString();
}
