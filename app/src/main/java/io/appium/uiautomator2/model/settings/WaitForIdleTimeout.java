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

import android.support.test.uiautomator.Configurator;

public class WaitForIdleTimeout extends AbstractSetting<Long> {

    private static final String SETTING_NAME = "waitForIdleTimeout";

    public WaitForIdleTimeout() {
        super(Long.class, SETTING_NAME);
    }

    @Override
    public Long getValue() {
        return Configurator.getInstance().getWaitForIdleTimeout();
    }

    @Override
    protected void apply(Long timeout) {
        Configurator.getInstance().setWaitForIdleTimeout(timeout);
    }

}
