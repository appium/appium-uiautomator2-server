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

package io.appium.uiautomator2.utils.actions_scheduler;

import java.util.Arrays;

import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepModel;

public class UnknownStepTypeException extends Exception {
    public UnknownStepTypeException(ScheduledActionStepModel model, String[] availableTypes) {
        super(String.format(
                "The value '%s' of '%s' field in step '%s' is unknown. " +
                        "Only the following kinds are supported: %s",
                model.type ,Constants.STEP_TYPE, model.name, Arrays.toString(availableTypes)
        ));
    }
}
