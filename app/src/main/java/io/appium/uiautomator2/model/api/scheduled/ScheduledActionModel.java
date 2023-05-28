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

package io.appium.uiautomator2.model.api.scheduled;

import java.util.List;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;

public class ScheduledActionModel extends BaseModel {
    @RequiredField
    public String name;
    @RequiredField
    public List<ScheduledActionStepModel> steps;
    public int times = 1;
    public long intervalMs = 1000L; // in milliseconds
    public int maxHistoryItems = 20;
    public int maxPass = -1;
    public int maxFail = -1;

    public ScheduledActionModel() {}
}
