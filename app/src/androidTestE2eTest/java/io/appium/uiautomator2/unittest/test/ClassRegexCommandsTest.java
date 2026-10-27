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
package io.appium.uiautomator2.unittest.test;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.unittest.fixture.ClassRegexActivity;
import io.appium.uiautomator2.unittest.test.internal.BaseTest;
import io.appium.uiautomator2.unittest.test.internal.Response;

import static io.appium.uiautomator2.unittest.test.internal.TestUtils.extractElementId;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElements;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClassRegexCommandsTest extends BaseTest {
    @Before
    @Override
    public void launchAUT() throws JSONException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        context.startActivity(new Intent(context, ClassRegexActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        assertTrue(waitForElement(By.accessibilityId("regex-button-1")).isSuccessful());
    }

    @Test
    public void findsMatchesAtTheSameIndexUnderDifferentParents() throws JSONException {
        // Both labels have index 0, so enumerating index(0), index(1), ... loses one.
        assertRegexMatchesExactClass("android.widget.TextView");
    }

    @Test
    public void findsMatchesWhenNoMatchHasIndexZero() throws JSONException {
        // Both buttons have index 1, so index(0) stops the old enumeration immediately.
        assertRegexMatchesExactClass("android.widget.Button");
    }

    @Test
    public void preservesExplicitInstance() throws JSONException {
        for (int i = 0; i < 2; i++) {
            Set<String> expected = elementIds(By.accessibilityId("regex-button-" + i));
            assertEquals(1, expected.size());
            assertEquals(expected, elementIds(By.androidUiAutomator(
                    selector("android.widget.Button", true) + ".instance(" + i + ")")));
        }
    }

    private void assertRegexMatchesExactClass(String className) throws JSONException {
        Set<String> expected = elementIds(By.androidUiAutomator(selector(className, false)));
        assertEquals("The fixture must expose two distinct matching elements", 2, expected.size());
        assertEquals(expected, elementIds(By.androidUiAutomator(selector(className, true))));
    }

    private String selector(String className, boolean regex) {
        return "new UiSelector().packageName(\"io.appium.uiautomator2.e2etest\")."
                + (regex ? "classNameMatches" : "className")
                + "(\"" + (regex ? className.replace(".", "[.]") : className) + "\")";
    }

    private Set<String> elementIds(By by) throws JSONException {
        Response response = findElements(by);
        assertTrue(by + " should succeed", response.isSuccessful());
        JSONArray elements = response.getValue();
        Set<String> result = new HashSet<>();
        for (int i = 0; i < elements.length(); i++) {
            String id = extractElementId(elements.getJSONObject(i));
            assertNotNull(id);
            assertTrue("Duplicate element: " + id, result.add(id));
        }
        return result;
    }
}
