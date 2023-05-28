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

import java.util.Objects;

import javax.annotation.Nullable;

import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepExceptionModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepResultModel;

public abstract class BaseActionStep {
    protected final ScheduledActionStepModel model;

    public BaseActionStep(ScheduledActionStepModel model) {
        this.model = model;
    }

    protected abstract String[] getSupportedSubtypes();

    protected String getSubtype() throws UnknownStepSubtypeException {
        if (!model.payload.containsKey(Constants.STEP_SUBTYPE)
                || !(model.payload.get(Constants.STEP_SUBTYPE) instanceof String)) {
            throw new UnknownStepSubtypeException(
                    model,
                    String.valueOf(model.payload.get(Constants.STEP_SUBTYPE)),
                    getSupportedSubtypes()
            );
        }
        return (String) Objects.requireNonNull(model.payload.get(Constants.STEP_SUBTYPE));
    }

    @Nullable
    protected abstract Object runInternalImplementation(String subtype) throws UnknownStepSubtypeException;

    public ScheduledActionStepResultModel run() {
        ScheduledActionStepResultModel result = new ScheduledActionStepResultModel(
                model.name,
                model.type,
                System.currentTimeMillis()
        );
        try {
            result.result = runInternalImplementation(getSubtype());
            result.passed = true;
        } catch (RuntimeException | UnknownStepSubtypeException e) {
            result.exception = new ScheduledActionStepExceptionModel(e);
            result.passed = false;
        }
        return result;
    }
}
