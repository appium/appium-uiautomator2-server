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

package io.appium.uiautomator2.utils;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

/**
 * Simple class for holding a String 2-tuple. An android class, and instance number, used for
 * finding elements by xpath.
 */
public class ClassInstancePair {

    private final String androidClass;
    private final String instance;

    public ClassInstancePair(String clazz, String inst) {
        androidClass = clazz;
        instance = inst;
    }

    public String getAndroidClass() {
        return androidClass;
    }

    public String getInstance() {
        return instance;
    }

    public BySelector getSelector() {
        String androidClass = getAndroidClass();

        //TODO: remove below comments once code get reviewed
        //below commented line related to UiAutomator V1(bootstrap) version, as we don't have possibility
        // in V2 version to use instance, so directly returning By.clazz
        // new UiSelector().className(androidClass).instance(Integer.parseInt(instance));
        return By.clazz(androidClass);
    }


}
