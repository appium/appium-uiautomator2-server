/*******************************************************************************
 * Copyright (c) 2025 Appium Contributors.
 * SPDX-License-Identifier: Apache-2.0
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.net.URL;

public final class TestResourceBundles {

    private static final TestResourceBundle CLASSPATH_ROOT = path -> {
        String normalized = path.startsWith("/") ? path.substring(1) : path;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL resource = loader.getResource(normalized);
        if (resource == null) {
            resource = loader.getResource("/" + normalized);
        }
        return resource;
    };

    private TestResourceBundles() {
    }

    public static TestResourceBundle get(String symbolicName) {
        switch (symbolicName) {
            case "org.w3c.xqts.testsuite":
            case "org.eclipse.wst.xml.xpath2.processor.tests":
                return CLASSPATH_ROOT;
            default:
                throw new IllegalArgumentException("Unknown test bundle: " + symbolicName);
        }
    }
}
