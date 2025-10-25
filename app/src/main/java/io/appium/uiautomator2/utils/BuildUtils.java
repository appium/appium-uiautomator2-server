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

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.test.BuildConfig;

public class BuildUtils {
    private static Map<String, Object> cachedBuildConfig;

    public static synchronized Map<String, Object> getBuildConfig() {
        if (cachedBuildConfig != null) {
            return cachedBuildConfig;
        }

        cachedBuildConfig = Arrays.stream(BuildConfig.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .collect(HashMap::new, (map, field) -> {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(null);
                        map.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        Logger.error("Field access denied for: " + field.getName(), e);
                    }
                }, HashMap::putAll);
        return cachedBuildConfig;
    }
}
