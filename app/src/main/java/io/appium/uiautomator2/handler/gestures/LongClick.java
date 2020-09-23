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

package io.appium.uiautomator2.handler.gestures;

import android.graphics.Point;
import android.graphics.Rect;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.gestures.LongClickModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class LongClick extends SafeRequestHandler {

    public LongClick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        LongClickModel longClickModel = toModel(request, LongClickModel.class);
        final String elementId = longClickModel.origin == null ? null : longClickModel.origin.getUnifiedId();
        if (elementId == null) {
            if (longClickModel.x == null && longClickModel.y != null
                    || longClickModel.x != null && longClickModel.y == null) {
                throw new IllegalArgumentException("Both x and y coordinates must be set");
            }
            CustomUiDevice.getInstance().getGestureController().longClick(
                    longClickModel.toNativePoint(),
                    longClickModel.duration == null ? null : longClickModel.duration.longValue()
            );
        } else {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new ElementNotFoundException();
            }
            if (longClickModel.x == null && longClickModel.y == null) {
                if (longClickModel.duration == null) {
                    element.longClick();
                } else {
                    element.longClick(longClickModel.duration.longValue());
                }
            } else if (longClickModel.x != null && longClickModel.y != null) {
                Rect bounds = element.getBounds();
                CustomUiDevice.getInstance().getGestureController().longClick(
                        new Point(bounds.left + longClickModel.x.intValue(), bounds.top + longClickModel.y.intValue()),
                        longClickModel.duration == null ? null : longClickModel.duration.longValue()
                );
            } else {
                throw new IllegalArgumentException("Both x and y coordinates must be set");
            }
        }

        return new AppiumResponse(getSessionId(request));
    }
}
