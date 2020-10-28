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

import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.model.By;

public enum ElementsLookupStrategy {
    BY_ID("id"),
    BY_XPATH("xpath"),
    BY_ACCESSIBILITY_ID("accessibility id"),
    BY_CLASS("class name"),
    BY_UIAUTOMATOR("-android uiautomator");

    private final String strategyName;

    ElementsLookupStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    @Override
    public String toString() {
        return strategyName;
    }

    public static ElementsLookupStrategy ofName(String strategyName) {
        List<String> supportedStrategies = new ArrayList<>();
        for (ElementsLookupStrategy by: values()) {
            if (by.toString().equals(strategyName)) {
                return by;
            }
            supportedStrategies.add(by.toString());
        }
        throw new InvalidSelectorException(String.format(
                "Selector strategy '%s' is not supported. Only the following selector strategies are supported: %s",
                strategyName, supportedStrategies));
    }

    public By toNativeSelector(String expression) {
        switch (this) {
            case BY_ID:
                return By.id(expression);
            case BY_CLASS:
                return By.className(expression);
            case BY_XPATH:
                return By.xpath(expression);
            case BY_ACCESSIBILITY_ID:
                return By.accessibilityId(expression);
            case BY_UIAUTOMATOR:
                return By.androidUiAutomator(expression);
            default:
                throw new IllegalStateException(
                        String.format("%s cannot be transformed to a selector", this.name()));
        }
    }
}
