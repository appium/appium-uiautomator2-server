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

import android.view.MotionEvent.PointerCoords;

import io.appium.uiautomator2.model.api.touch.appium.TouchGestureModel;
import io.appium.uiautomator2.model.api.touch.appium.TouchActionsModel;
import io.appium.uiautomator2.model.api.touch.appium.TouchLocationModel;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;

import java.util.List;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class MultiPointerGesture extends SafeRequestHandler {
    private static final double INTERSTEP_DELAY_SEC = 0.005;

    public MultiPointerGesture(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        final PointerCoords[][] pcs = parsePointerCoords(request);
        if (!UiAutomatorBridge.getInstance().getInteractionController().performMultiPointerGesture(pcs)) {
            throw new InvalidElementStateException("Unable to perform multi pointer gesture");
        }
        return new AppiumResponse(getSessionId(request));
    }

    private PointerCoords[][] parsePointerCoords(final IHttpRequest request) {
        TouchActionsModel model = toModel(request, TouchActionsModel.class);
        final double time = computeLongestTime(model.actions);
        final PointerCoords[][] pcs = new PointerCoords[model.actions.size()][];
        for (int i = 0; i < model.actions.size(); i++) {
            final List<TouchGestureModel> gestures = model.actions.get(i);
            pcs[i] = gesturesToPointerCoords(time, gestures);
        }
        return pcs;
    }

    private double computeLongestTime(List<List<TouchGestureModel>> actions) {
        double max = 0.0;
        for (final List<TouchGestureModel> gestures : actions) {
            final double endTime = gestures.get(gestures.size() - 1).time;
            if (endTime > max) {
                max = endTime;
            }
        }
        return max;
    }

    private PointerCoords[] gesturesToPointerCoords(final double maxTime, List<TouchGestureModel> gestures) {
        // gestures, e.g.:
        // [
        // {"touch":{"y":529.5,"x":120},"time":0.2},
        // {"touch":{"y":529.5,"x":130},"time":0.4},
        // {"touch":{"y":454.5,"x":140},"time":0.6},
        // {"touch":{"y":304.5,"x":150},"time":0.8}
        // ]

        // From the docs:
        // "Steps are injected about 5 milliseconds apart, so 100 steps may take
        // around 0.5 seconds to complete."
        final int steps = (int) (maxTime * 200) + 2;
        final PointerCoords[] pc = new PointerCoords[steps];
        TouchGestureModel current = gestures.get(0);
        int gestureIndex = 1;
        double currentTime = current.time;
        double runningTime = 0.0;
        for (int step = 0; step < steps; step++) {
            if (runningTime > currentTime && gestureIndex < gestures.size()) {
                current = gestures.get(gestureIndex++);
                currentTime = current.time;
            }
            pc[step] = toPointerCoords(current);
            runningTime += INTERSTEP_DELAY_SEC;
        }
        return pc;
    }

    private PointerCoords toPointerCoords(TouchGestureModel gesture) {
        final TouchLocationModel touch = gesture.touch;
        final PointerCoords p = new PointerCoords();
        p.size = 1;
        p.pressure = 1;
        p.x = touch.x.intValue();
        p.y = touch.y.intValue();
        return p;
    }
}
