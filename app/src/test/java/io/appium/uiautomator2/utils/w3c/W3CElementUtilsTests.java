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

import android.graphics.Rect;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.utils.Point;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class W3CElementUtilsTests {

    @Test
    public void verifyCanExtractElementIdFromJSONWP() throws JSONException {
        JSONObject elementInfo = new JSONObject("{" +
                "\"ELEMENT\": \"123\"," +
                "}");
        String elementId = W3CElementUtils.extractElementId(elementInfo);
        assertEquals(elementId, "123");
    }

    @Test
    public void verifyCanExtractElementIdFromW3C() throws JSONException {
        JSONObject elementInfo = new JSONObject("{" +
                "\"element-6066-11e4-a52e-4f735466cecf\": \"123\"," +
                "}");
        String elementId = W3CElementUtils.extractElementId(elementInfo);
        assertEquals(elementId, "123");
    }

    @Test
    public void verifyCanExtractElementIdFromHybrid() throws JSONException {
        JSONObject elementInfo = new JSONObject("{" +
                "\"element-6066-11e4-a52e-4f735466cecf\": \"123\"," +
                "\"ELEMENT\": \"123\"," +
                "}");
        String elementId = W3CElementUtils.extractElementId(elementInfo);
        assertEquals(elementId, "123");
    }

    @Test
    public void verifyNullIsReturnedForNoElementId() throws JSONException {
        JSONObject elementInfo = new JSONObject("{" +
                "\"element-6066-11e4-a52e-4f735466c\": \"123\"," +
                "}");
        String elementId = W3CElementUtils.extractElementId(elementInfo);
        assertNull(elementId);
    }

    @Test
    public void verifyElementIdsAreAttached() throws JSONException {
        JSONObject elementInfo = new JSONObject();
        W3CElementUtils.attachElementId(new AndroidElement() {
            @Nullable
            @Override
            public By getBy() {
                return null;
            }

            @Nullable
            @Override
            public String getContextId() {
                return null;
            }

            @Override
            public boolean isSingleMatch() {
                return false;
            }

            @Override
            public void clear() throws UiObjectNotFoundException {

            }

            @Override
            public void click() throws UiObjectNotFoundException {

            }

            @Override
            public boolean longClick() throws UiObjectNotFoundException {
                return false;
            }

            @Override
            public String getText() throws UiObjectNotFoundException {
                return null;
            }

            @Override
            public String getName() throws UiObjectNotFoundException {
                return null;
            }

            @Override
            public String getAttribute(String attr) throws UiObjectNotFoundException {
                return null;
            }

            @Override
            public boolean setText(String text) {
                return false;
            }

            @Override
            public String getId() {
                return "123";
            }

            @Override
            public Rect getBounds() throws UiObjectNotFoundException {
                return null;
            }

            @Override
            public Object getChild(Object sel) throws UiObjectNotFoundException, InvalidSelectorException, ClassNotFoundException {
                return null;
            }

            @Override
            public List<Object> getChildren(Object selector, By by) throws UiObjectNotFoundException, InvalidSelectorException, ClassNotFoundException {
                return null;
            }

            @Override
            public String getContentDesc() throws UiObjectNotFoundException {
                return null;
            }

            @Override
            public Object getUiObject() {
                return null;
            }

            @Override
            public Point getAbsolutePosition(Point point) throws UiObjectNotFoundException, InvalidCoordinatesException {
                return null;
            }

            @Override
            public boolean dragTo(int destX, int destY, int steps) throws UiObjectNotFoundException, InvalidCoordinatesException {
                return false;
            }

            @Override
            public boolean dragTo(Object destObj, int steps) throws UiObjectNotFoundException, InvalidCoordinatesException {
                return false;
            }
        }, elementInfo);
        assertEquals(elementInfo.getString("ELEMENT"), "123");
        assertEquals(elementInfo.getString("element-6066-11e4-a52e-4f735466cecf"), "123");

    }
}
