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

package io.appium.uiautomator2.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlertHelpersTests {

    private static Pattern regularAlertTitleResIdPattern() {
        try {
            Field field = AlertHelpers.class.getDeclaredField("regularAlertTitleResIdPattern");
            field.setAccessible(true);
            return (Pattern) field.get(null);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Unable to read regularAlertTitleResIdPattern", e);
        }
    }

    @Test
    public void shouldMatchAllKnownRegularAlertTitleIds() {
        Pattern pattern = regularAlertTitleResIdPattern();
        String[] matchingIds = {
                "android:id/alertTitle",
                "android:id/alert_title",
                "android:id/custom",
                "com.example.app:id/alertTitle",
                "com.example.app:id/alert_title",
                "com.example.app:id/custom"
        };
        for (String resId : matchingIds) {
            assertTrue(resId, pattern.matcher(resId).matches());
        }
    }

    @Test
    public void shouldNotMatchUnrelatedIds() {
        Pattern pattern = regularAlertTitleResIdPattern();
        String[] nonMatchingIds = {
                "alertTitle",
                "android:id/alertTitles",
                "android:id/alert_titles",
                "android:id/customView",
                "android:id/message",
                "android:id/permission_message"
        };
        for (String resId : nonMatchingIds) {
            assertFalse(resId, pattern.matcher(resId).matches());
        }
    }
}
