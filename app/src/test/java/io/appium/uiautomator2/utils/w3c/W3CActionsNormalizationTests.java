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

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.appium.uiautomator2.utils.w3c.ActionHelpers.normalizeSequence;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class W3CActionsNormalizationTests {
    @Test
    public void verifySortingOrderInNormalizedEvents() {
        MotionEvent.PointerProperties down1Props = new MotionEvent.PointerProperties();
        down1Props.id = 0;
        MotionEvent.PointerProperties down2Props = new MotionEvent.PointerProperties();
        down2Props.id = 1;
        MotionEvent.PointerProperties up1Props = new MotionEvent.PointerProperties();
        up1Props.id = 0;
        MotionEvent.PointerProperties up2Props = new MotionEvent.PointerProperties();
        up2Props.id = 1;

        List<MotionInputEventParams> allEvents = new ArrayList<>();
        allEvents.add(new MotionInputEventParams(0, MotionEvent.ACTION_DOWN, null, 0, down2Props));
        allEvents.add(new MotionInputEventParams(0, MotionEvent.ACTION_DOWN, null, 0, down1Props));
        allEvents.add(new MotionInputEventParams(0, MotionEvent.ACTION_MOVE, null, 0, null));
        allEvents.add(new MotionInputEventParams(0, MotionEvent.ACTION_MOVE, null, 0, null));
        allEvents.add(new MotionInputEventParams(0, MotionEvent.ACTION_UP, null, 0, up1Props));
        allEvents.add(new MotionInputEventParams(0, MotionEvent.ACTION_UP, null, 0, up2Props));

        List<MotionInputEventParams> normalizedEvents = normalizeSequence(allEvents);

        assertThat(normalizedEvents.size(), is(equalTo(6)));
        assertThat(normalizedEvents.get(0).actionCode, is(equalTo(MotionEvent.ACTION_DOWN)));
        assertThat(normalizedEvents.get(0).properties.id, is(equalTo(0)));
        assertThat(normalizedEvents.get(1).actionCode, is(equalTo(MotionEvent.ACTION_DOWN)));
        assertThat(normalizedEvents.get(1).properties.id, is(equalTo(1)));
        assertThat(normalizedEvents.get(2).actionCode, is(equalTo(MotionEvent.ACTION_UP)));
        assertThat(normalizedEvents.get(2).properties.id, is(equalTo(1)));
        assertThat(normalizedEvents.get(3).actionCode, is(equalTo(MotionEvent.ACTION_UP)));
        assertThat(normalizedEvents.get(3).properties.id, is(equalTo(0)));
        assertThat(normalizedEvents.get(4).actionCode, is(equalTo(MotionEvent.ACTION_MOVE)));
        assertThat(normalizedEvents.get(5).actionCode, is(equalTo(MotionEvent.ACTION_MOVE)));
    }
}
