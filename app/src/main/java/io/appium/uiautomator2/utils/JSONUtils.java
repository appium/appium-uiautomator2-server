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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    @NonNull
    public static Integer readInteger(JSONObject payload, String name) throws JSONException {
        //noinspection ConstantConditions
        return readInteger(payload, name, true);
    }

    @Nullable
    public static Integer readInteger(JSONObject payload, String name, boolean isRequired)
            throws JSONException {
        if (!payload.has(name)) {
            if (isRequired) {
                throw new IllegalArgumentException(String.format("'%s' parameter is mandatory", name));
            }
            return null;
        }

        final Object objValue = payload.get(name);
        if (objValue instanceof Integer) {
            return  (Integer) objValue;
        } else if (objValue instanceof Long) {
            return ((Long) objValue).intValue();
        } else if (objValue instanceof String) {
            return Integer.parseInt((String) objValue);
        }
        if (isRequired) {
            throw new IllegalArgumentException(String.format(
                    "'%s' parameter should be a valid integer number. '%s' is given instead",
                    name, objValue));
        }
        return null;
    }
}
