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
import io.appium.uiautomator2.model.api.gestures.DoubleClickModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

public class DoubleClick extends BaseGesture {
    @Nullable
    public static Object perform(DoubleClickModel doubleClickModel) {
        final String elementId = doubleClickModel.origin == null
                ? null
                : doubleClickModel.origin.getUnifiedId();
        AndroidElement element = null;
        if (elementId == null && doubleClickModel.locator != null) {
            element = locateElement(doubleClickModel.locator);
        } else if (elementId != null) {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            element = session.getElementsCache().get(elementId);
        }
        if (element == null) {
            if (doubleClickModel.offset == null) {
                throw new IllegalArgumentException("Double click offset coordinates must be provided " +
                        "if element is not set");
            }
            CustomUiDevice.getInstance().getGestureController().doubleClick(
                    doubleClickModel.offset.toNativePoint()
            );
        } else {
            if (doubleClickModel.offset == null) {
                element.doubleClick();
            } else {
                Rect bounds = element.getBounds();
                Point location = new Point(bounds.left + doubleClickModel.offset.x.intValue(),
                        bounds.top + doubleClickModel.offset.y.intValue());
                CustomUiDevice.getInstance().getGestureController().doubleClick(location);
            }
        }
        return null;
    }
}
