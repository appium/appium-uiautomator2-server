/*******************************************************************************
 * Copyright (c) 2025 Appium Contributors.
 * SPDX-License-Identifier: Apache-2.0
 *
 * Minimal replacement for Eclipse/OSGi bundle resource lookup when running
 * vendored PsychoPath tests outside of an OSGi runtime.
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.net.URL;

public interface TestResourceBundle {

    URL getEntry(String path);
}
