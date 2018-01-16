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

import android.util.LongSparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.util.List;

import io.appium.uiautomator2.utils.w3c.ActionsHelpers.InputEventParams;
import io.appium.uiautomator2.utils.w3c.ActionsHelpers.KeyInputEventParams;
import io.appium.uiautomator2.utils.w3c.ActionsHelpers.MotionInputEventParams;

import static io.appium.uiautomator2.utils.w3c.ActionsHelpers.actionsToInputEventsMapping;
import static io.appium.uiautomator2.utils.w3c.ActionsHelpers.preprocessActions;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class W3CActionsTransformationTests {

    @Test
    public void verifyValidInputEventsChainIsCompiledForNoneAction() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"none\"," +
                "\"id\": \"none1\"," +
                "\"actions\": [" +
                "{\"type\": \"pause\", \"duration\": 200}," +
                "{\"type\": \"pause\", \"duration\": 20}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(2));

        assertThat(eventsChain.keyAt(0), equalTo(200L));
        assertThat(eventsChain.valueAt(0).size(), equalTo(0));

        assertThat(eventsChain.keyAt(1), equalTo(220L));
        assertThat(eventsChain.valueAt(1).size(), equalTo(0));
    }


    @Test
    public void verifyValidInputEventsChainIsCompiledForSingleKeysGesture() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"\u2000\"}," +
                "{\"type\": \"keyDown\", \"value\": \"A\"}," +
                "{\"type\": \"keyUp\", \"value\": \"A\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\u2000\"}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(1));
        final List<InputEventParams> generatedParams = eventsChain.valueAt(0);
        assertThat(generatedParams.size(), equalTo(4));

        for (final InputEventParams params : generatedParams) {
            assertThat(params.startDelta, equalTo(0L));
            assertThat(params, is(instanceOf(KeyInputEventParams.class)));
        }

        assertThat(((KeyInputEventParams) generatedParams.get(0)).keyAction,
                equalTo(KeyEvent.ACTION_DOWN));
        assertThat(((KeyInputEventParams) generatedParams.get(0)).keyCode, equalTo(0x2000));

        assertThat(((KeyInputEventParams) generatedParams.get(1)).keyAction,
                equalTo(KeyEvent.ACTION_DOWN));
        assertThat(((KeyInputEventParams) generatedParams.get(1)).keyCode, equalTo(65));

        assertThat(((KeyInputEventParams) generatedParams.get(2)).keyAction,
                equalTo(KeyEvent.ACTION_UP));
        assertThat(((KeyInputEventParams) generatedParams.get(2)).keyCode, equalTo(65));

        assertThat(((KeyInputEventParams) generatedParams.get(3)).keyAction,
                equalTo(KeyEvent.ACTION_UP));
        assertThat(((KeyInputEventParams) generatedParams.get(3)).keyCode, equalTo(0x2000));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForSingleKeysGestureWithPause() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"A\"}," +
                "{\"type\": \"pause\", \"duration\": 500}," +
                "{\"type\": \"keyUp\", \"value\": \"A\"}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(2));

        final List<InputEventParams> paramSet1 = eventsChain.valueAt(0);
        assertThat(paramSet1.size(), equalTo(1));
        final KeyInputEventParams downParams = (KeyInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.keyAction, equalTo(KeyEvent.ACTION_DOWN));
        assertThat(downParams.keyCode, equalTo(65));


        assertThat(eventsChain.keyAt(1), equalTo(500L));
        final List<InputEventParams> paramSet2 = eventsChain.valueAt(1);
        assertThat(paramSet2.size(), equalTo(1));
        final KeyInputEventParams upParams = (KeyInputEventParams) paramSet2.get(0);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.keyAction, equalTo(KeyEvent.ACTION_UP));
        assertThat(upParams.keyCode, equalTo(65));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForSingleFingerGestureWithKeys() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"finger1\"," +
                "\"parameters\": {\"pointerType\": \"touch\"}," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pause\", \"duration\": 10}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\"}]" +
                "}, {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"pause\", \"duration\": 10}," +
                "{\"type\": \"keyDown\", \"value\": \"\u2000\"}," +
                "{\"type\": \"pause\", \"duration\": 20}," +
                "{\"type\": \"keyUp\", \"value\": \"\u2000\"}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(5));

        final List<InputEventParams> paramSet1 = eventsChain.valueAt(0);
        assertThat(eventsChain.keyAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(1));

        final MotionInputEventParams downParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(downParams.button, equalTo(0));
        assertEquals(downParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(downParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(downParams.properties.id, is(equalTo(0)));
        assertThat(downParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet2 = eventsChain.valueAt(1);
        assertThat(eventsChain.keyAt(1), equalTo(10L));
        assertThat(paramSet2.size(), equalTo(2));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move1Params.button, equalTo(0));
        assertEquals(move1Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(move1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final KeyInputEventParams keyDownParams = (KeyInputEventParams) paramSet2.get(1);
        assertThat(keyDownParams.startDelta, equalTo(10L));
        assertThat(keyDownParams.keyAction, equalTo(KeyEvent.ACTION_DOWN));
        assertThat(keyDownParams.keyCode, equalTo(0x2000));

        final List<InputEventParams> paramSet3 = eventsChain.valueAt(2);
        assertThat(eventsChain.keyAt(2), equalTo(15L));
        assertThat(paramSet3.size(), equalTo(1));

        final MotionInputEventParams move2Params = (MotionInputEventParams) paramSet3.get(0);
        assertThat(move2Params.startDelta, equalTo(0L));
        assertThat(move2Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move2Params.button, equalTo(0));
        assertEquals(move2Params.coordinates.x, 75.0, Math.ulp(1.0));
        assertEquals(move2Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move2Params.properties.id, is(equalTo(0)));
        assertThat(move2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet4 = eventsChain.valueAt(3);
        assertThat(eventsChain.keyAt(3), equalTo(20L));
        assertThat(paramSet4.size(), equalTo(1));

        final MotionInputEventParams upParams = (MotionInputEventParams) paramSet4.get(0);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(upParams.button, equalTo(0));
        assertEquals(upParams.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(upParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(upParams.properties.id, is(equalTo(0)));
        assertThat(upParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet5 = eventsChain.valueAt(4);
        assertThat(eventsChain.keyAt(4), equalTo(30L));
        assertThat(paramSet5.size(), equalTo(1));

        final KeyInputEventParams keyUpParams = (KeyInputEventParams) paramSet5.get(0);
        assertThat(keyUpParams.startDelta, equalTo(10L));
        assertThat(keyUpParams.keyAction, equalTo(KeyEvent.ACTION_UP));
        assertThat(keyUpParams.keyCode, equalTo(0x2000));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForSingleFingerGesture() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"finger1\"," +
                "\"parameters\": {\"pointerType\": \"touch\"}," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pause\", \"duration\": 10}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\"}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(4));

        final List<InputEventParams> paramSet1 = eventsChain.valueAt(0);
        assertThat(eventsChain.keyAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(1));

        final MotionInputEventParams downParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(downParams.button, equalTo(0));
        assertEquals(downParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(downParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(downParams.properties.id, is(equalTo(0)));
        assertThat(downParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet2 = eventsChain.valueAt(1);
        assertThat(eventsChain.keyAt(1), equalTo(10L));
        assertThat(paramSet2.size(), equalTo(1));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move1Params.button, equalTo(0));
        assertEquals(move1Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(move1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet3 = eventsChain.valueAt(2);
        assertThat(eventsChain.keyAt(2), equalTo(15L));
        assertThat(paramSet3.size(), equalTo(1));

        final MotionInputEventParams move2Params = (MotionInputEventParams) paramSet3.get(0);
        assertThat(move2Params.startDelta, equalTo(0L));
        assertThat(move2Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move2Params.button, equalTo(0));
        assertEquals(move2Params.coordinates.x, 75.0, Math.ulp(1.0));
        assertEquals(move2Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move2Params.properties.id, is(equalTo(0)));
        assertThat(move2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet4 = eventsChain.valueAt(3);
        assertThat(eventsChain.keyAt(3), equalTo(20L));
        assertThat(paramSet4.size(), equalTo(1));

        final MotionInputEventParams upParams = (MotionInputEventParams) paramSet4.get(0);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(upParams.button, equalTo(0));
        assertEquals(upParams.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(upParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(upParams.properties.id, is(equalTo(0)));
        assertThat(upParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForMouseGestureWithMultipleMoves()
            throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\", \"button\": 2}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": 100, \"y\": 0}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"x\": 0, \"y\": 0}," +
                "{\"type\": \"pointerUp\", \"button\": 2}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(7));

        final List<InputEventParams> paramSet1 = eventsChain.valueAt(0);
        assertThat(eventsChain.keyAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(2));

        final MotionInputEventParams downParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(downParams.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(downParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(downParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(downParams.properties.id, is(equalTo(0)));
        assertThat(downParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet1.get(1);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move1Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move1Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(move1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet2 = eventsChain.valueAt(1);
        assertThat(eventsChain.keyAt(1), equalTo(5L));
        assertThat(paramSet2.size(), equalTo(1));

        final MotionInputEventParams move2Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move2Params.startDelta, equalTo(0L));
        assertThat(move2Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move2Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move2Params.coordinates.x, 75.0, Math.ulp(1.0));
        assertEquals(move2Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move2Params.properties.id, is(equalTo(0)));
        assertThat(move2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet3 = eventsChain.valueAt(2);
        assertThat(eventsChain.keyAt(2), equalTo(10L));
        assertThat(paramSet3.size(), equalTo(1));

        final MotionInputEventParams move3Params = (MotionInputEventParams) paramSet3.get(0);
        assertThat(move3Params.startDelta, equalTo(0L));
        assertThat(move3Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move3Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move3Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(move3Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move3Params.properties.id, is(equalTo(0)));
        assertThat(move3Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet4 = eventsChain.valueAt(3);
        assertThat(eventsChain.keyAt(3), equalTo(15L));
        assertThat(paramSet4.size(), equalTo(1));

        final MotionInputEventParams move4Params = (MotionInputEventParams) paramSet4.get(0);
        assertThat(move4Params.startDelta, equalTo(0L));
        assertThat(move4Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move4Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move4Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(move4Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move4Params.properties.id, is(equalTo(0)));
        assertThat(move4Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet5 = eventsChain.valueAt(4);
        assertThat(eventsChain.keyAt(4), equalTo(20L));
        assertThat(paramSet5.size(), equalTo(1));

        final MotionInputEventParams move5Params = (MotionInputEventParams) paramSet5.get(0);
        assertThat(move5Params.startDelta, equalTo(0L));
        assertThat(move5Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move5Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move5Params.coordinates.x, 150.0, Math.ulp(1.0));
        assertEquals(move5Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move5Params.properties.id, is(equalTo(0)));
        assertThat(move5Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet6 = eventsChain.valueAt(5);
        assertThat(eventsChain.keyAt(5), equalTo(25L));
        assertThat(paramSet6.size(), equalTo(1));

        final MotionInputEventParams move6Params = (MotionInputEventParams) paramSet6.get(0);
        assertThat(move6Params.startDelta, equalTo(0L));
        assertThat(move6Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move6Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move6Params.coordinates.x, 75.0, Math.ulp(1.0));
        assertEquals(move6Params.coordinates.y, 50.0, Math.ulp(1.0));
        assertThat(move6Params.properties.id, is(equalTo(0)));
        assertThat(move6Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet7 = eventsChain.valueAt(6);
        assertThat(eventsChain.keyAt(6), equalTo(30L));
        assertThat(paramSet7.size(), equalTo(1));

        final MotionInputEventParams upParams = (MotionInputEventParams) paramSet7.get(0);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(upParams.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(upParams.coordinates.x, 0.0, Math.ulp(1.0));
        assertEquals(upParams.coordinates.y, 0.0, Math.ulp(1.0));
        assertThat(upParams.properties.id, is(equalTo(0)));
        assertThat(upParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForMouseGestureWithHoverGesture()
            throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"x\": 0, \"y\": 0}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(3));

        final List<InputEventParams> paramSet1 = eventsChain.valueAt(0);
        assertThat(eventsChain.keyAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(2));

        final MotionInputEventParams enterParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(enterParams.startDelta, equalTo(0L));
        assertThat(enterParams.actionCode, equalTo(MotionEvent.ACTION_HOVER_ENTER));
        assertThat(enterParams.button, equalTo(0));
        assertEquals(enterParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(enterParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(enterParams.properties.id, is(equalTo(0)));
        assertThat(enterParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet1.get(1);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_HOVER_MOVE));
        assertThat(move1Params.button, equalTo(0));
        assertEquals(move1Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(move1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet2 = eventsChain.valueAt(1);
        assertThat(eventsChain.keyAt(1), equalTo(5L));
        assertThat(paramSet2.size(), equalTo(1));

        final MotionInputEventParams move2Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move2Params.startDelta, equalTo(0L));
        assertThat(move2Params.actionCode, equalTo(MotionEvent.ACTION_HOVER_MOVE));
        assertThat(move2Params.button, equalTo(0));
        assertEquals(move2Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(move2Params.coordinates.y, 50.0, Math.ulp(1.0));
        assertThat(move2Params.properties.id, is(equalTo(0)));
        assertThat(move2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet3 = eventsChain.valueAt(2);
        assertThat(eventsChain.keyAt(2), equalTo(10L));
        assertThat(paramSet3.size(), equalTo(1));

        final MotionInputEventParams exitParams = (MotionInputEventParams) paramSet3.get(0);
        assertThat(exitParams.startDelta, equalTo(0L));
        assertThat(exitParams.actionCode, equalTo(MotionEvent.ACTION_HOVER_EXIT));
        assertThat(exitParams.button, equalTo(0));
        assertEquals(exitParams.coordinates.x, 0.0, Math.ulp(1.0));
        assertEquals(exitParams.coordinates.y, 0.0, Math.ulp(1.0));
        assertThat(exitParams.properties.id, is(equalTo(0)));
        assertThat(exitParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForMultipleFingersGesture() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"finger1\"," +
                "\"parameters\": {\"pointerType\": \"touch\"}," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 0, \"y\": 0}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pause\", \"duration\": 10}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": 50, \"y\": 50}," +
                "{\"type\": \"pointerUp\"}]" +
                "}, { " +
                "\"type\": \"pointer\"," +
                "\"id\": \"finger2\"," +
                "\"parameters\": {\"pointerType\": \"touch\"}," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pause\", \"duration\": 10}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": -50, \"y\": -50}," +
                "{\"type\": \"pointerUp\"}]" +
                "} ]");
        final LongSparseArray<List<InputEventParams>> eventsChain = actionsToInputEventsMapping(
                preprocessActions(actionJson)
        );
        assertThat(eventsChain.size(), equalTo(4));

        final List<InputEventParams> paramSet1 = eventsChain.valueAt(0);
        assertThat(eventsChain.keyAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(2));

        final MotionInputEventParams down1Params = (MotionInputEventParams) paramSet1.get(0);
        assertThat(down1Params.startDelta, equalTo(0L));
        assertThat(down1Params.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(down1Params.button, equalTo(0));
        assertEquals(down1Params.coordinates.x, 0.0, Math.ulp(1.0));
        assertEquals(down1Params.coordinates.y, 0.0, Math.ulp(1.0));
        assertThat(down1Params.properties.id, is(equalTo(0)));
        assertThat(down1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final MotionInputEventParams down2Params = (MotionInputEventParams) paramSet1.get(1);
        assertThat(down2Params.startDelta, equalTo(0L));
        assertThat(down2Params.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(down2Params.button, equalTo(0));
        assertEquals(down2Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(down2Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(down2Params.properties.id, is(equalTo(1)));
        assertThat(down2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet2 = eventsChain.valueAt(1);
        assertThat(eventsChain.keyAt(1), equalTo(10L));
        assertThat(paramSet2.size(), equalTo(2));

        final MotionInputEventParams move11Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move11Params.startDelta, equalTo(0L));
        assertThat(move11Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move11Params.button, equalTo(0));
        assertEquals(move11Params.coordinates.x, 0.0, Math.ulp(1.0));
        assertEquals(move11Params.coordinates.y, 0.0, Math.ulp(1.0));
        assertThat(move11Params.properties.id, is(equalTo(0)));
        assertThat(move11Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final MotionInputEventParams move12Params = (MotionInputEventParams) paramSet2.get(1);
        assertThat(move12Params.startDelta, equalTo(0L));
        assertThat(move12Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move12Params.button, equalTo(0));
        assertEquals(move12Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(move12Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move12Params.properties.id, is(equalTo(1)));
        assertThat(move12Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet3 = eventsChain.valueAt(2);
        assertThat(eventsChain.keyAt(2), equalTo(15L));
        assertThat(paramSet3.size(), equalTo(2));

        final MotionInputEventParams move21Params = (MotionInputEventParams) paramSet3.get(0);
        assertThat(move21Params.startDelta, equalTo(0L));
        assertThat(move21Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move21Params.button, equalTo(0));
        assertEquals(move21Params.coordinates.x, 25.0, Math.ulp(1.0));
        assertEquals(move21Params.coordinates.y, 25.0, Math.ulp(1.0));
        assertThat(move21Params.properties.id, is(equalTo(0)));
        assertThat(move21Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final MotionInputEventParams move22Params = (MotionInputEventParams) paramSet3.get(1);
        assertThat(move22Params.startDelta, equalTo(0L));
        assertThat(move22Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move22Params.button, equalTo(0));
        assertEquals(move22Params.coordinates.x, 75.0, Math.ulp(1.0));
        assertEquals(move22Params.coordinates.y, 75.0, Math.ulp(1.0));
        assertThat(move22Params.properties.id, is(equalTo(1)));
        assertThat(move22Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet4 = eventsChain.valueAt(3);
        assertThat(eventsChain.keyAt(3), equalTo(20L));
        assertThat(paramSet4.size(), equalTo(2));

        final MotionInputEventParams up1Params = (MotionInputEventParams) paramSet4.get(0);
        assertThat(up1Params.startDelta, equalTo(0L));
        assertThat(up1Params.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(up1Params.button, equalTo(0));
        assertEquals(up1Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(up1Params.coordinates.y, 50.0, Math.ulp(1.0));
        assertThat(up1Params.properties.id, is(equalTo(0)));
        assertThat(up1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final MotionInputEventParams up2Params = (MotionInputEventParams) paramSet4.get(1);
        assertThat(up2Params.startDelta, equalTo(0L));
        assertThat(up2Params.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(up2Params.button, equalTo(0));
        assertEquals(up2Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(up2Params.coordinates.y, 50.0, Math.ulp(1.0));
        assertThat(up2Params.properties.id, is(equalTo(1)));
        assertThat(up2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfDurationIsNegative() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\", \"button\": 1}," +
                "{\"type\": \"pointerMove\", \"duration\": -1, \"origin\": \"pointer\", \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\", \"button\": 1}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfNoStaringCoordinatesAreProvided() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerDown\", \"button\": 1}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\", \"button\": 1}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfInvalidPointerActionTypeIsProvided()
            throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 0, \"y\": 0}," +
                "{\"type\": \"pointerBla\", \"button\": 1}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\", \"button\": 1}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfInvalidKeyActionTypeIsProvided()
            throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"\u2000\"}," +
                "{\"type\": \"keyBla\", \"value\": \"A\"}," +
                "{\"type\": \"keyUp\", \"value\": \"A\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\u2000\"}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfKeyValueIsMissing() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"\u2000\"}," +
                "{\"type\": \"keyUp\"}," +
                "{\"type\": \"keyUp\", \"value\": \"A\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\u2000\"}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfKeyValueIsEmpty() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"\u2000\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\"}," +
                "{\"type\": \"keyUp\", \"value\": \"A\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\u2000\"}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfInvalidNoneActionTypeIsProvided()
            throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"none\"," +
                "\"id\": \"none1\"," +
                "\"actions\": [" +
                "{\"type\": \"pause\", \"duration\": 200}," +
                "{\"type\": \"bla\", \"duration\": 20}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfNoDurationIsProvided() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\", \"button\": 1}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\", \"button\": 1}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfCoordinateIsMissing() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"y\": 100}," +
                "{\"type\": \"pointerDown\", \"button\": 1}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\", \"button\": 1}]" +
                "} ]");
        actionsToInputEventsMapping(preprocessActions(actionJson));
    }
}
