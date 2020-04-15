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

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.utils.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class W3CCapsUtils {
    public static final String FIRST_MATCH_KEY = "firstMatch";
    public static final String ALWAYS_MATCH_KEY = "alwaysMatch";

    private static final String[] STANDARD_CAPS = new String[]{
            "browserName",
            "browserVersion",
            "platformName",
            "acceptInsecureCerts",
            "pageLoadStrategy",
            "proxy",
            "setWindowRect",
            "timeouts",
            "unhandledPromptBehavior"
    };
	private static final String APPIUM_PREFIX = "appium";

    private static boolean isStandardCap(String capName) {
        for (String standardCap : STANDARD_CAPS) {
            if (standardCap.equalsIgnoreCase(capName)) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, Object> mergeCaps(Map<String, Object> primary, Map<String, Object> secondary) {
        Map<String, Object> result = new LinkedHashMap<>(primary);
        for (Map.Entry<String, Object> entry : secondary.entrySet()) {
            if (result.containsKey(entry.getKey())) {
                throw new InvalidArgumentException(String.format(
                        "Property '%s' should not exist on both primary (%s) and secondary (%s) objects",
                        entry.getKey(), primary, secondary));
            }
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private static Map<String, Object> stripPrefixes(Map<String, Object> caps) {
        final String prefix = APPIUM_PREFIX + ":";
        Map<String, Object> filteredCaps = new LinkedHashMap<>();
        List<String> badPrefixedCaps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : caps.entrySet()) {
            if (!entry.getKey().startsWith(prefix)) {
                filteredCaps.put(entry.getKey(), entry.getValue());
                continue;
            }
            String strippedName = entry.getKey().substring(prefix.length());
            filteredCaps.put(strippedName, entry.getValue());
            if (isStandardCap(strippedName)) {
                badPrefixedCaps.add(strippedName);
            }
        }
        if (!badPrefixedCaps.isEmpty()) {
            throw new InvalidArgumentException(String.format(
                    "The capabilities %s are standard capabilities and should not have the '%s' prefix",
                    badPrefixedCaps, prefix));
        }
        return filteredCaps;
    }

    public static Map<String, Object> parseCapabilities(Map<String, Object> caps) {
        //noinspection unchecked
        Map<String, Object> alwaysMatch = caps.containsKey(ALWAYS_MATCH_KEY)
                ? (Map<String, Object>) caps.get(ALWAYS_MATCH_KEY)
                : null;
        if (alwaysMatch == null) {
            alwaysMatch = Collections.emptyMap();
        }
        //noinspection unchecked
        List<Map<String, Object>> firstMatch = caps.containsKey(FIRST_MATCH_KEY)
                ? (List<Map<String, Object>>) caps.get(FIRST_MATCH_KEY)
                : null;
        if (firstMatch == null) {
            firstMatch = Collections.emptyList();
        }

        List<Map<String, Object>> allFirstMatchCaps = firstMatch.isEmpty()
                ? Collections.singletonList(Collections.<String, Object>emptyMap())
                : firstMatch;
        Map<String, Object> requiredCaps = stripPrefixes(alwaysMatch);
        for (Map<String, Object> fmc : allFirstMatchCaps) {
            Map<String, Object> strippedCaps = stripPrefixes(fmc);
            try {
                return mergeCaps(requiredCaps, strippedCaps);
            } catch (Exception e) {
                Logger.warn(e);
            }
        }
        throw new InvalidArgumentException(String.format(
                "Could not find matching capabilities from %s", caps.toString()));
    }
}
