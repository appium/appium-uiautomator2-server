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

package io.appium.uiautomator2.utils.w3c;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActionHelpers {

    private static List<MotionInputEventParams> sortEventsByPointerIndex(List<MotionInputEventParams> events,
                                                                         boolean ascending) {
        List<MotionInputEventParams> sortedEvents = new ArrayList<>(events);
        Collections.sort(sortedEvents, new Comparator<MotionInputEventParams>() {
            @Override
            public int compare(MotionInputEventParams o1, MotionInputEventParams o2) {
                return Integer.compare(o1.properties.id, o2.properties.id);
            }
        });
        if (!ascending) {
            Collections.reverse(sortedEvents);
        }
        return sortedEvents;
    }

    public static List<MotionInputEventParams> normalizeSequence(List<MotionInputEventParams> events) {
        List<MotionInputEventParams> normalizedEvents = new ArrayList<>();
        List<MotionInputEventParams> pointerDownEvents = new ArrayList<>();
        List<MotionInputEventParams> pointerUpEvents = new ArrayList<>();
        List<MotionInputEventParams> moveEvents = new ArrayList<>();
        for (MotionInputEventParams eventParams : events) {
            switch (eventParams.actionCode) {
                case MotionEvent.ACTION_UP:
                    pointerUpEvents.add(eventParams);
                    break;
                case MotionEvent.ACTION_DOWN:
                    pointerDownEvents.add(eventParams);
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveEvents.add(eventParams);
                    break;
                default:
                    normalizedEvents.add(eventParams);
                    break;
            }
        }
        normalizedEvents.addAll(sortEventsByPointerIndex(pointerDownEvents, true));
        normalizedEvents.addAll(sortEventsByPointerIndex(pointerUpEvents, false));
        normalizedEvents.addAll(moveEvents);
        return normalizedEvents;
    }
}
