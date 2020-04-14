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

import androidx.annotation.Nullable;
import io.appium.uiautomator2.model.AndroidElement;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import static io.appium.uiautomator2.utils.w3c.ElementConstants.JWP_ELEMENT_ID_KEY_NAME;
import static io.appium.uiautomator2.utils.w3c.ElementConstants.W3C_ELEMENT_ID_KEY_NAME;

public class W3CElementUtils {
    @Nullable
    public static String extractElementId(JSONObject obj) {
        Iterator<String> keysIterator = obj.keys();
        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            if ((key.equalsIgnoreCase(JWP_ELEMENT_ID_KEY_NAME) ||
                    key.equalsIgnoreCase(W3C_ELEMENT_ID_KEY_NAME))
                    && (obj.opt(key) instanceof String)) {
                return (String) obj.opt(key);
            }
        }
        return null;
    }

    public static void attachElementId(AndroidElement element, JSONObject destination)
            throws JSONException {
        destination.put(JWP_ELEMENT_ID_KEY_NAME, element.getId());
        destination.put(W3C_ELEMENT_ID_KEY_NAME, element.getId());
    }
}
