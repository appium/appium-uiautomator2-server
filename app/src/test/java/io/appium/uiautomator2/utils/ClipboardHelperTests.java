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
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import io.appium.uiautomator2.test.BuildConfig;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ClipboardHelperTests {

    @Test
    public void verifySettingAndGettingClipboardContent() {
        final String text = "Appium is the best of the best";
        final ClipboardHelper clipboardHelper = new ClipboardHelper(ShadowApplication
                .getInstance()
                .getApplicationContext());
        clipboardHelper.setTextData(null, text);
        assertEquals(clipboardHelper.getTextData(), text);
    }

}
