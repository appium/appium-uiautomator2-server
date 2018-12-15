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

package io.appium.uiautomator2.handler;

import android.os.SystemClock;

import org.json.JSONException;

import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.InteractionController;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.utils.Logger;

public class TouchLongClick extends TouchEvent {

    public TouchLongClick(String mappedUri) {
        super(mappedUri);
    }

    protected static boolean correctLongClick(final int x, final int y, final int duration) {
        try {
            InteractionController interactionController = UiAutomatorBridge.getInstance().getInteractionController();
            if (interactionController.touchDown(x, y)) {
                SystemClock.sleep(duration);
                return interactionController.touchUp(x, y);
            }
            return false;
        } catch (final Exception e) {
            Logger.debug("Problem invoking correct long click: " + e);
            return false;
        }
    }

    @Override
    protected boolean executeTouchEvent() throws UiObjectNotFoundException,
            UiAutomator2Exception, JSONException {
        int duration = params.has("duration")
                ? Integer.parseInt(params.getString("duration"))
                : 2000;
        printEventDebugLine("TouchLongClick", duration);
        if (correctLongClick(clickX, clickY, duration)) {
            return true;
        }
        // if correctLongClick failed and we have an element
        // then uiautomator's longClick is used as a fallback.
        if (element != null) {
            Logger.debug("Falling back to broken longClick");
            return element.longClick();
        }
        return false;
    }
}
