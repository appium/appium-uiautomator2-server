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

import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.core.InteractionController;
import io.appium.uiautomator2.utils.Logger;

public class TouchLongClick extends BaseTouchAction {
    private static final int DEFAULT_DURATION_MS = 2000;

    public TouchLongClick(String mappedUri) {
        super(mappedUri);
    }

    private boolean performLongClick(final int x, final int y, final int duration) {
        InteractionController ic = getIc();
        boolean isSuccessful = ic.touchDown(x, y);
        if (isSuccessful) {
            SystemClock.sleep(duration);
            isSuccessful = ic.touchUp(x, y);
        }
        return isSuccessful;
    }

    @Override
    protected void executeEvent() throws UiObjectNotFoundException {
        int duration = params.duration != null ? params.duration : DEFAULT_DURATION_MS;
        printEventDebugLine(duration);

        if (performLongClick(clickX, clickY, duration)) {
            return;
        }

        if (element == null) {
            throw new InvalidElementStateException(
                    String.format("Cannot perform %s action at (%s, %s)", getName(), clickX, clickY));
        }

        // if correctLongClick failed and we have an element
        // then uiautomator's longClick is used as a fallback.
        Logger.debug("Falling back to broken longClick");
        if (!element.longClick()) {
            throw new InvalidElementStateException(
                    String.format("Cannot perform %s action at %s element", getName(), element.getBy()));
        }
    }
}
