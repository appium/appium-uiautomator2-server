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

package io.appium.uiautomator2.handler;

import java.util.HashSet;
import java.util.Set;

import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.Attribute;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;

/**
 * Get page source. Return as string of XML doc
 */
public class Source extends SafeRequestHandler {
    private static final Set<Attribute> includedAttributes = new HashSet<>();

    private static synchronized Set<Attribute> getXmlSourceAttributes() {
        if (includedAttributes.isEmpty()) {
            for (Attribute attribute : Attribute.values()) {
                if (attribute.isExposableToXml()) {
                    includedAttributes.add(attribute);
                }
            }
        }
        return includedAttributes;
    }

    public Source(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        refreshAccessibilityCache();
        String xmlSource = new AccessibilityNodeInfoDumper(null, getXmlSourceAttributes()).dumpToXml();
        return new AppiumResponse(getSessionId(request), xmlSource);
    }
}
