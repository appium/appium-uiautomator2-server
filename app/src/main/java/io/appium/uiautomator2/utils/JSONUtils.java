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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSONUtils {
    @NonNull
    public static Integer readInteger(JSONObject payload, String name) throws JSONException {
        //noinspection ConstantConditions
        return readInteger(payload, name, true);
    }

    public static Object formatNull(Object value) {
        return value == null ? JSONObject.NULL : value;
    }

    @Nullable
    public static Integer readInteger(JSONObject payload, String name, boolean isRequired) throws JSONException {
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

    public static Map<String, Object> toMap(JSONObject obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> result = new LinkedHashMap<>();
        Iterator<String> keysItr = obj.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = obj.opt(key);
            if (value instanceof JSONObject) {
                result.put(key, toMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                result.put(key, toList((JSONArray) value));
            } else {
                result.put(key, value);
            }
        }
        return result;
    }

    public static List<Object> toList(JSONArray obj) {
        if (obj == null) {
            return null;
        }

        List<Object> result = new ArrayList<>();
        for (int idx = 0; idx < obj.length(); ++idx) {
            Object value = obj.opt(idx);
            if (value instanceof JSONArray) {
                result.add(toList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                result.add(toMap((JSONObject) value));
            } else {
                result.add(value);
            }
        }
        return result;
    }
}
