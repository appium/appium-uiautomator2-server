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

import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepModel;
import io.appium.uiautomator2.utils.ScreenshotHelper;

public class ScreenshotStep extends BaseActionStep {
    public static final String TYPE = "screenshot";
    private static final String PNG = "png";

    public ScreenshotStep(ScheduledActionStepModel model) {
        super(model);
    }

    @Override
    protected String[] getSupportedSubtypes() {
        return new String[]{PNG};
    }

    @Override
    protected Object runInternalImplementation(String subtype) throws UnknownStepSubtypeException {
        if (PNG.equals(subtype)) {
            return ScreenshotHelper.takeScreenshot();
        }
        throw new UnknownStepSubtypeException(model, subtype, getSupportedSubtypes());
    }
}
