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

import java.util.Map;

import javax.annotation.Nullable;

import io.appium.uiautomator2.model.api.gestures.ClickModel;
import io.appium.uiautomator2.model.api.gestures.DoubleClickModel;
import io.appium.uiautomator2.model.api.gestures.LongClickModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepModel;
import io.appium.uiautomator2.utils.gestures.Click;
import io.appium.uiautomator2.utils.gestures.DoubleClick;
import io.appium.uiautomator2.utils.gestures.LongClick;

public class GestureStep extends BaseActionStep {
    public static final String TYPE = "gesture";
    private static final String SUBTYPE_CLICK = "click";
    private static final String SUBTYPE_DOUBLE_CLICK = "doubleClick";
    private static final String SUBTYPE_LONG_CLICK = "longClick";

    public GestureStep (ScheduledActionStepModel model) {
        super(model);
    }

    @Override
    protected String[] getSupportedSubtypes() {
        return new String[] {SUBTYPE_CLICK, SUBTYPE_DOUBLE_CLICK, SUBTYPE_LONG_CLICK};
    }

    @Nullable
    @Override
    protected Object runInternalImplementation(String subtype) throws UnknownStepSubtypeException {
        switch (subtype) {
            case SUBTYPE_CLICK:
                return performClick(model.payload);
            case SUBTYPE_DOUBLE_CLICK:
                return performDoubleClick(model.payload);
            case SUBTYPE_LONG_CLICK:
                return performLongClick(model.payload);
            default:
                throw new UnknownStepSubtypeException(model, subtype, getSupportedSubtypes());
        }
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
}
