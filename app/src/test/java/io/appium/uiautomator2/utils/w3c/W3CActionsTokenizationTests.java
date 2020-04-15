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

import android.view.KeyEvent;
import android.view.MotionEvent;

import com.google.gson.reflect.TypeToken;
import io.appium.uiautomator2.model.api.touch.w3c.W3CItemModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.util.List;

import static io.appium.uiautomator2.utils.ModelUtils.toObject;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class W3CActionsTokenizationTests {
    private static final ActionsPreprocessor actionsPreprocessor = new ActionsPreprocessor();
    private static final ActionsTokenizer actionsTokenizer = new ActionsTokenizer();

    private static List<W3CItemModel> toActionItems(JSONArray json) {
        //noinspection unchecked
        return (List<W3CItemModel>) toObject(json, new TypeToken<List<W3CItemModel>>() { }.getType());
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForNoneAction() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"none\"," +
                "\"id\": \"none1\"," +
                "\"actions\": [" +
                "{\"type\": \"pause\", \"duration\": 200}," +
                "{\"type\": \"pause\", \"duration\": 20}]" +
                "} ]");
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(2));

        assertThat(eventsChain.timeDeltaAt(0), equalTo(200L));
        assertThat(eventsChain.eventsAtIndex(0).size(), equalTo(0));

        assertThat(eventsChain.timeDeltaAt(1), equalTo(220L));
        assertThat(eventsChain.eventsAtIndex(1).size(), equalTo(0));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForSingleKeysGesture() throws JSONException {
        final int keyCode = 0xE019;
        final int metaCode = 0xE009;
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"\uE009\"}," +
                "{\"type\": \"keyDown\", \"value\": \"\uE019\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\uE019\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\uE009\"}]" +
                "} ]");
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(1));
        final List<InputEventParams> generatedParams = eventsChain.eventsAtIndex(0);
        assertThat(generatedParams.size(), equalTo(4));

        for (final InputEventParams params : generatedParams) {
            assertThat(params.startDelta, equalTo(0L));
            assertThat(params, is(instanceOf(KeyInputEventParams.class)));
        }

        assertThat(((KeyInputEventParams) generatedParams.get(0)).keyAction,
                equalTo(KeyEvent.ACTION_DOWN));
        assertThat(((KeyInputEventParams) generatedParams.get(0)).keyCode, equalTo(metaCode));

        assertThat(((KeyInputEventParams) generatedParams.get(1)).keyAction,
                equalTo(KeyEvent.ACTION_DOWN));
        assertThat(((KeyInputEventParams) generatedParams.get(1)).keyCode, equalTo(keyCode));

        assertThat(((KeyInputEventParams) generatedParams.get(2)).keyAction,
                equalTo(KeyEvent.ACTION_UP));
        assertThat(((KeyInputEventParams) generatedParams.get(2)).keyCode, equalTo(keyCode));

        assertThat(((KeyInputEventParams) generatedParams.get(3)).keyAction,
                equalTo(KeyEvent.ACTION_UP));
        assertThat(((KeyInputEventParams) generatedParams.get(3)).keyCode, equalTo(metaCode));
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
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(2));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(paramSet1.size(), equalTo(1));
        final KeyInputEventParams downParams = (KeyInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.keyAction, equalTo(KeyEvent.ACTION_DOWN));
        assertThat(downParams.keyCode, equalTo(65));


        assertThat(eventsChain.timeDeltaAt(1), equalTo(500L));
        final List<InputEventParams> paramSet2 = eventsChain.eventsAtIndex(1);
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
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(3));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(eventsChain.timeDeltaAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(1));

        final MotionInputEventParams downParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(downParams.button, equalTo(0));
        assertEquals(downParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(downParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(downParams.properties.id, is(equalTo(0)));
        assertThat(downParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet2 = eventsChain.eventsAtIndex(1);
        assertThat(eventsChain.timeDeltaAt(1), equalTo(20L));
        assertThat(paramSet2.size(), equalTo(2));

        final KeyInputEventParams keyDownParams = (KeyInputEventParams) paramSet2.get(0);
        assertThat(keyDownParams.startDelta, equalTo(20L));
        assertThat(keyDownParams.keyAction, equalTo(KeyEvent.ACTION_DOWN));
        assertThat(keyDownParams.keyCode, equalTo(0x2000));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet2.get(1);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move1Params.button, equalTo(0));
        assertEquals(move1Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(move1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet3 = eventsChain.eventsAtIndex(2);
        assertThat(eventsChain.timeDeltaAt(2), equalTo(40L));
        assertThat(paramSet3.size(), equalTo(2));

        final KeyInputEventParams keyUpParams = (KeyInputEventParams) paramSet3.get(0);
        assertThat(keyUpParams.startDelta, equalTo(20L));
        assertThat(keyUpParams.keyAction, equalTo(KeyEvent.ACTION_UP));
        assertThat(keyUpParams.keyCode, equalTo(0x2000));

        final MotionInputEventParams upParams = (MotionInputEventParams) paramSet3.get(1);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(upParams.button, equalTo(0));
        assertEquals(upParams.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(upParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(upParams.properties.id, is(equalTo(0)));
        assertThat(upParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));
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
                "{\"type\": \"pause\", \"duration\": 7}," +
                "{\"type\": \"pointerMove\", \"duration\": 10, \"origin\": \"pointer\", \"x\": -50, \"y\": 0}," +
                "{\"type\": \"pointerUp\"}]" +
                "} ]");
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(3));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(eventsChain.timeDeltaAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(1));

        final MotionInputEventParams downParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(downParams.button, equalTo(0));
        assertEquals(downParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(downParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(downParams.properties.id, is(equalTo(0)));
        assertThat(downParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet2 = eventsChain.eventsAtIndex(1);
        assertThat(eventsChain.timeDeltaAt(1), equalTo(20L));
        assertThat(paramSet2.size(), equalTo(1));

        final MotionInputEventParams move2Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move2Params.startDelta, equalTo(0L));
        assertThat(move2Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move2Params.button, equalTo(0));
        assertEquals(move2Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(move2Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move2Params.properties.id, is(equalTo(0)));
        assertThat(move2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet3 = eventsChain.eventsAtIndex(2);
        assertThat(eventsChain.timeDeltaAt(2), equalTo(40L));
        assertThat(paramSet3.size(), equalTo(1));

        final MotionInputEventParams upParams = (MotionInputEventParams) paramSet3.get(0);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(upParams.button, equalTo(0));
        assertEquals(upParams.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(upParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(upParams.properties.id, is(equalTo(0)));
        assertThat(upParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForSingleFingerTapGesture() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"finger1\"," +
                "\"parameters\": {\"pointerType\": \"touch\"}," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pointerUp\"}]" +
                "} ]");
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(1));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(eventsChain.timeDeltaAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(2));

        final MotionInputEventParams downParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(downParams.button, equalTo(0));
        assertEquals(downParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(downParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(downParams.properties.id, is(equalTo(0)));
        assertThat(downParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final MotionInputEventParams upParams = (MotionInputEventParams) paramSet1.get(1);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(upParams.button, equalTo(0));
        assertEquals(upParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(upParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(upParams.properties.id, is(equalTo(0)));
        assertThat(upParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForDoubleTapGesture() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"finger1\"," +
                "\"parameters\": {\"pointerType\": \"touch\"}," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pause\", \"duration\": 100}," +
                "{\"type\": \"pointerUp\"}," +
                "{\"type\": \"pause\", \"duration\": 40}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pause\", \"duration\": 100}," +
                "{\"type\": \"pointerUp\"}]" +
                "} ]");
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(4));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(eventsChain.timeDeltaAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(1));

        final MotionInputEventParams down1Params = (MotionInputEventParams) paramSet1.get(0);
        assertThat(down1Params.startDelta, equalTo(0L));
        assertThat(down1Params.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(down1Params.button, equalTo(0));
        assertEquals(down1Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(down1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(down1Params.properties.id, is(equalTo(0)));
        assertThat(down1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet2 = eventsChain.eventsAtIndex(1);
        assertThat(eventsChain.timeDeltaAt(1), equalTo(100L));
        assertThat(paramSet2.size(), equalTo(1));

        final MotionInputEventParams up1Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(up1Params.startDelta, equalTo(0L));
        assertThat(up1Params.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(up1Params.button, equalTo(0));
        assertEquals(up1Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(up1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(up1Params.properties.id, is(equalTo(0)));
        assertThat(up1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet3 = eventsChain.eventsAtIndex(2);
        assertThat(eventsChain.timeDeltaAt(2), equalTo(140L));
        assertThat(paramSet3.size(), equalTo(1));

        final MotionInputEventParams down2Params = (MotionInputEventParams) paramSet3.get(0);
        assertThat(down2Params.startDelta, equalTo(140L));
        assertThat(down2Params.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(down2Params.button, equalTo(0));
        assertEquals(down2Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(down2Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(down2Params.properties.id, is(equalTo(0)));
        assertThat(down2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet4 = eventsChain.eventsAtIndex(3);
        assertThat(eventsChain.timeDeltaAt(3), equalTo(240L));
        assertThat(paramSet4.size(), equalTo(1));

        final MotionInputEventParams up2Params = (MotionInputEventParams) paramSet4.get(0);
        assertThat(up2Params.startDelta, equalTo(140L));
        assertThat(up2Params.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(up2Params.button, equalTo(0));
        assertEquals(up2Params.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(up2Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(up2Params.properties.id, is(equalTo(0)));
        assertThat(up2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));
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
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(4));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(eventsChain.timeDeltaAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(1));

        final MotionInputEventParams downParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(downParams.startDelta, equalTo(0L));
        assertThat(downParams.actionCode, equalTo(MotionEvent.ACTION_DOWN));
        assertThat(downParams.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(downParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(downParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(downParams.properties.id, is(equalTo(0)));
        assertThat(downParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet2 = eventsChain.eventsAtIndex(1);
        assertThat(eventsChain.timeDeltaAt(1), equalTo(20L));
        assertThat(paramSet2.size(), equalTo(2));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move1Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move1Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(move1Params.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet3 = eventsChain.eventsAtIndex(2);
        assertThat(eventsChain.timeDeltaAt(2), equalTo(40L));

        final MotionInputEventParams move2Params = (MotionInputEventParams) paramSet3.get(0);
        assertThat(move2Params.startDelta, equalTo(0L));
        assertThat(move2Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move2Params.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(move2Params.coordinates.x, 0.0, Math.ulp(1.0));
        assertEquals(move2Params.coordinates.y, 0, Math.ulp(1.0));
        assertThat(move2Params.properties.id, is(equalTo(0)));
        assertThat(move2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet4 = eventsChain.eventsAtIndex(3);
        assertThat(eventsChain.timeDeltaAt(3), equalTo(60L));
        assertThat(paramSet4.size(), equalTo(1));

        final MotionInputEventParams upParams = (MotionInputEventParams) paramSet4.get(0);
        assertThat(upParams.startDelta, equalTo(0L));
        assertThat(upParams.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(upParams.button, equalTo(MotionEvent.BUTTON_SECONDARY));
        assertEquals(upParams.coordinates.x, 0.0, Math.ulp(1.0));
        assertEquals(upParams.coordinates.y, 0.0, Math.ulp(1.0));
        assertThat(upParams.properties.id, is(equalTo(0)));
        assertThat(upParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));
    }

    @Test
    public void verifyValidInputEventsChainIsCompiledForMouseGestureWithHoverGesture()
            throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"parameters\": {\"pointerType\": \"mouse\"}," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"x\": 100, \"y\": 100}," +
                "{\"type\": \"pointerMove\", \"duration\": 30, \"x\": 0, \"y\": 0}]" +
                "} ]");
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(3));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(eventsChain.timeDeltaAt(0), equalTo(0L));
        assertThat(paramSet1.size(), equalTo(1));

        final MotionInputEventParams enterParams = (MotionInputEventParams) paramSet1.get(0);
        assertThat(enterParams.startDelta, equalTo(0L));
        assertThat(enterParams.actionCode, equalTo(MotionEvent.ACTION_HOVER_ENTER));
        assertThat(enterParams.button, equalTo(0));
        assertEquals(enterParams.coordinates.x, 100.0, Math.ulp(1.0));
        assertEquals(enterParams.coordinates.y, 100.0, Math.ulp(1.0));
        assertThat(enterParams.properties.id, is(equalTo(0)));
        assertThat(enterParams.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet2 = eventsChain.eventsAtIndex(1);
        assertThat(eventsChain.timeDeltaAt(1), equalTo(20L));
        assertThat(paramSet2.size(), equalTo(1));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_HOVER_MOVE));
        assertThat(move1Params.button, equalTo(0));
        assertEquals(move1Params.coordinates.x, 33.3, .1);
        assertEquals(move1Params.coordinates.y, 33.3, .1);
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_MOUSE));

        final List<InputEventParams> paramSet3 = eventsChain.eventsAtIndex(2);
        assertThat(eventsChain.timeDeltaAt(2), equalTo(40L));
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
        final ActionTokens eventsChain = actionsTokenizer.tokenize(
                actionsPreprocessor.preprocess(toActionItems(actionJson))
        );
        assertThat(eventsChain.size(), equalTo(3));

        final List<InputEventParams> paramSet1 = eventsChain.eventsAtIndex(0);
        assertThat(eventsChain.timeDeltaAt(0), equalTo(0L));
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

        final List<InputEventParams> paramSet2 = eventsChain.eventsAtIndex(1);
        assertThat(eventsChain.timeDeltaAt(1), equalTo(20L));
        assertThat(paramSet2.size(), equalTo(2));

        final MotionInputEventParams move1Params = (MotionInputEventParams) paramSet2.get(0);
        assertThat(move1Params.startDelta, equalTo(0L));
        assertThat(move1Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move1Params.button, equalTo(0));
        assertEquals(move1Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(move1Params.coordinates.y, 50.0, Math.ulp(1.0));
        assertThat(move1Params.properties.id, is(equalTo(0)));
        assertThat(move1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final MotionInputEventParams move2Params = (MotionInputEventParams) paramSet2.get(1);
        assertThat(move2Params.startDelta, equalTo(0L));
        assertThat(move2Params.actionCode, equalTo(MotionEvent.ACTION_MOVE));
        assertThat(move2Params.button, equalTo(0));
        assertEquals(move2Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(move2Params.coordinates.y, 50.0, Math.ulp(1.0));
        assertThat(move2Params.properties.id, is(equalTo(1)));
        assertThat(move2Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final List<InputEventParams> paramSet3 = eventsChain.eventsAtIndex(2);
        assertThat(eventsChain.timeDeltaAt(2), equalTo(40L));
        assertThat(paramSet3.size(), equalTo(2));

        final MotionInputEventParams up1Params = (MotionInputEventParams) paramSet3.get(0);
        assertThat(up1Params.startDelta, equalTo(0L));
        assertThat(up1Params.actionCode, equalTo(MotionEvent.ACTION_UP));
        assertThat(up1Params.button, equalTo(0));
        assertEquals(up1Params.coordinates.x, 50.0, Math.ulp(1.0));
        assertEquals(up1Params.coordinates.y, 50.0, Math.ulp(1.0));
        assertThat(up1Params.properties.id, is(equalTo(0)));
        assertThat(up1Params.properties.toolType, equalTo(MotionEvent.TOOL_TYPE_FINGER));

        final MotionInputEventParams up2Params = (MotionInputEventParams) paramSet3.get(1);
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
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
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfTwoOrMoreDownActionsExistAtTheSameTime() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"y\": 100}," +
                "{\"type\": \"pointerDown\", \"button\": 1}," +
                "{\"type\": \"pointerDown\", \"button\": 1}," +
                "{\"type\": \"pointerUp\", \"button\": 1}]" +
                "} ]");
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfTwoOrMoreUpActionsExistAtTheSameTime() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"y\": 100}," +
                "{\"type\": \"pointerUp\", \"button\": 1}," +
                "{\"type\": \"pointerUp\", \"button\": 1}]" +
                "} ]");
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
    }

    @Test(expected = ActionsParseException.class)
    public void verifyChainCompilationFailsIfUpActionPreceedsDownAtTheSameTime() throws JSONException {
        final JSONArray actionJson = new JSONArray("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"mouse1\"," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"y\": 100}," +
                "{\"type\": \"pointerUp\", \"button\": 1}," +
                "{\"type\": \"pointerDown\", \"button\": 1}]" +
                "} ]");
        actionsTokenizer.tokenize(actionsPreprocessor.preprocess(toActionItems(actionJson)));
    }
}

