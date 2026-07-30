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

import android.content.pm.ActivityInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ActivityOrientationListenerTests {

    @Parameterized.Parameters(name = "{0} -> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED, "SCREEN_ORIENTATION_UNSPECIFIED"},
                {ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, "SCREEN_ORIENTATION_LANDSCAPE"},
                {ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, "SCREEN_ORIENTATION_PORTRAIT"},
                {ActivityInfo.SCREEN_ORIENTATION_USER, "SCREEN_ORIENTATION_USER"},
                {ActivityInfo.SCREEN_ORIENTATION_SENSOR, "SCREEN_ORIENTATION_SENSOR"},
                {ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR, "SCREEN_ORIENTATION_FULL_SENSOR"},
                {ActivityInfo.SCREEN_ORIENTATION_LOCKED, "SCREEN_ORIENTATION_LOCKED"},
                {-999, null},
        });
    }

    private final int orientationValue;
    private final String expectedName;

    public ActivityOrientationListenerTests(int orientationValue, String expectedName) {
        this.orientationValue = orientationValue;
        this.expectedName = expectedName;
    }

    @Test
    public void shouldMapScreenOrientationConstant() {
        assertEquals(expectedName,
                ActivityOrientationListener.screenOrientationConstantName(orientationValue));
    }
}
