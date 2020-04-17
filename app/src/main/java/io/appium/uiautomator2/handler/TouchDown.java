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

package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

public class TouchDown extends BaseTouchAction {

    public TouchDown(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected void executeEvent() throws UiAutomator2Exception {
        printEventDebugLine();

        if (!getIc().touchDown(clickX, clickY)) {
            throw new InvalidElementStateException(
                    String.format("Cannot perform %s action at (%s, %s)", getName(), clickX, clickY));
        }
    }
}
