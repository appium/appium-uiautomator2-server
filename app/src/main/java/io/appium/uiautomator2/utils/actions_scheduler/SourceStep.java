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

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;

import java.util.Collections;

import javax.annotation.Nullable;

import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepModel;

public class SourceStep extends BaseActionStep {
    public static final String TYPE = "source";
    private static final String XML = "xml";

    public SourceStep(ScheduledActionStepModel model) {
        super(model);
    }

    @Override
    protected String[] getSupportedSubtypes() {
        return new String[]{XML};
    }

    @Override
    @Nullable
    protected Object runInternalImplementation(String subtype) throws UnknownStepSubtypeException {
        if (XML.equals(subtype)) {
            return fetchXmlSource();
        }
        throw new UnknownStepSubtypeException(model, subtype, getSupportedSubtypes());
    }

    private String fetchXmlSource() {
        refreshAccessibilityCache();
        return new AccessibilityNodeInfoDumper(null, Collections.emptySet()).dumpToXml();
    }
}
