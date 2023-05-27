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

import static io.appium.uiautomator2.utils.ModelUtils.toJsonString;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

import android.util.Log;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.model.api.gestures.ClickModel;
import io.appium.uiautomator2.model.api.gestures.DoubleClickModel;
import io.appium.uiautomator2.model.api.gestures.LongClickModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepExceptionModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepResultModel;
import io.appium.uiautomator2.utils.gestures.Click;
import io.appium.uiautomator2.utils.gestures.DoubleClick;
import io.appium.uiautomator2.utils.gestures.LongClick;

public class GestureStep {
    public static final String TYPE = "gesture";
    private static final String KIND = "kind";
    private static final String CLICK = "click";
    private static final String DOUBLE_CLICK = "doubleClick";
    private static final String LONG_CLICK = "longClick";

    private final ScheduledActionStepModel model;

    public GestureStep (ScheduledActionStepModel model) {
        this.model = model;
    }

    private Object performClick(Map<?, ?> payload) {
        return Click.perform(toModel(toJsonString(payload), ClickModel.class));
    }

    private Object performDoubleClick(Map<?, ?> payload) {
        return DoubleClick.perform(toModel(toJsonString(payload), DoubleClickModel.class));
    }

    private Object performLongClick(Map<?, ?> payload) {
        return LongClick.perform(toModel(toJsonString(payload), LongClickModel.class));
    }

    public ScheduledActionStepResultModel run() {
        ScheduledActionStepResultModel result = new ScheduledActionStepResultModel(
                model.name,
                model.type,
                System.currentTimeMillis()
        );
        if (!model.payload.containsKey(KIND) || !(model.payload.get(KIND) instanceof String)) {
            throw new InvalidArgumentException(String.format(
               "The payload of '%s' step (type '%s') must contain a valid '%s' value",
               model.name, model.type, KIND
            ));
        }
        String kind = (String) Objects.requireNonNull(model.payload.get(KIND));
        RuntimeException error = null;
        Object stepResult = null;
        try {
            switch (kind) {
                case CLICK:
                    stepResult = performClick(model.payload);
                    break;
                case DOUBLE_CLICK:
                    stepResult = performDoubleClick(model.payload);
                    break;
                case LONG_CLICK:
                    stepResult = performLongClick(model.payload);
                    break;
                default:
                    throw new InvalidArgumentException(String.format(
                            "The value of '%s' field in step '%s' (type '%s') is unknown. " +
                                    "Only the following kinds are supported: %s",
                            kind, model.name, model.type, Arrays.toString(new String[]{
                                    CLICK, DOUBLE_CLICK, LONG_CLICK
                            })
                    ));
            }
        } catch (InvalidArgumentException e) {
            throw e;
        } catch (RuntimeException e) {
            error = e;
        }
        if (error == null) {
            result.passed = true;
            result.result = stepResult;
        } else {
            result.passed = false;
            result.exception = new ScheduledActionStepExceptionModel(
                    error.getClass().getName(),
                    error.getMessage(),
                    Log.getStackTraceString(error)
            );
        }
        return result;
    }
}
