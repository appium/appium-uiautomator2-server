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

package io.appium.uiautomator2.model;

import android.content.pm.ActivityInfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ActivityOrientationListenerTests {

    @Test
    public void shouldMapKnownScreenOrientationConstants() {
        assertEquals("SCREEN_ORIENTATION_UNSPECIFIED",
                ActivityOrientationListener.screenOrientationConstantName(
                        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED));
        assertEquals("SCREEN_ORIENTATION_LANDSCAPE",
                ActivityOrientationListener.screenOrientationConstantName(
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE));
        assertEquals("SCREEN_ORIENTATION_PORTRAIT",
                ActivityOrientationListener.screenOrientationConstantName(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
        assertEquals("SCREEN_ORIENTATION_USER",
                ActivityOrientationListener.screenOrientationConstantName(
                        ActivityInfo.SCREEN_ORIENTATION_USER));
        assertEquals("SCREEN_ORIENTATION_SENSOR",
                ActivityOrientationListener.screenOrientationConstantName(
                        ActivityInfo.SCREEN_ORIENTATION_SENSOR));
        assertEquals("SCREEN_ORIENTATION_FULL_SENSOR",
                ActivityOrientationListener.screenOrientationConstantName(
                        ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR));
        assertEquals("SCREEN_ORIENTATION_LOCKED",
                ActivityOrientationListener.screenOrientationConstantName(
                        ActivityInfo.SCREEN_ORIENTATION_LOCKED));
    }

    @Test
    public void shouldReturnNullForUnknownScreenOrientationConstant() {
        assertNull(ActivityOrientationListener.screenOrientationConstantName(-999));
    }
}
