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

package io.appium.uiautomator2.utils.gestures;

import android.graphics.Point;
import android.graphics.Rect;

import javax.annotation.Nullable;

import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.gestures.ClickModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

public class Click extends BaseGesture {
    @Nullable
    public static Object perform(ClickModel clickModel) {
        String elementId = clickModel.origin == null ? null : clickModel.origin.getUnifiedId();
        AndroidElement element = null;
        if (elementId == null && clickModel.locator != null) {
            element = locateElement(clickModel.locator);
        } else if (elementId != null) {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            element = session.getElementsCache().get(elementId);
        }
        if (element == null) {
            if (clickModel.offset == null) {
                throw new IllegalArgumentException("Click offset coordinates must be provided " +
                        "if element is not set");
            }
            CustomUiDevice.getInstance().getGestureController().click(
                    clickModel.offset.toNativePoint()
            );
        } else {
            if (clickModel.offset == null) {
                element.click();
            } else {
                Rect bounds = element.getBounds();
                Point location = new Point(
                        bounds.left + clickModel.offset.x.intValue(),
                        bounds.top + clickModel.offset.y.intValue()
                );
                CustomUiDevice.getInstance().getGestureController().click(location);
            }
        }
        return null;
    }
}
