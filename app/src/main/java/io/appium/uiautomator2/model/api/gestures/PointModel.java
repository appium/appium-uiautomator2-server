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

package io.appium.uiautomator2.model.api.gestures;

import android.graphics.Point;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;

public class PointModel extends BaseModel {
    @RequiredField
    public Double x;
    @RequiredField
    public Double y;

    public Point toNativePoint() {
        return new Point(x.intValue(), y.intValue());
    }

    public io.appium.uiautomator2.model.Point toPoint() {
        return new io.appium.uiautomator2.model.Point(x, y);
    }
}
