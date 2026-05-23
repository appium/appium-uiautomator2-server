/*******************************************************************************
 * Copyright (c) 2025 Appium Contributors.
 * SPDX-License-Identifier: Apache-2.0
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.util.Enumeration;

import junit.framework.TestFailure;
import junit.framework.TestResult;

/**
 * JUnit Platform entry point for the upstream {@link AllPsychoPathTests} suite.
 */
public class PsychoPathTestSuiteAdapter {

    @org.junit.Test
    public void runAllPsychoPathTests() {
        junit.framework.Test suite = AllPsychoPathTests.suite();
        TestResult result = new TestResult();
        suite.run(result);

        if (!result.wasSuccessful()) {
            StringBuilder message = new StringBuilder();
            message.append(String.format(
                    "Ran %d tests: %d failures, %d errors.%n",
                    result.runCount(),
                    result.failureCount(),
                    result.errorCount()));
            appendFailures(message, "Failures", result.failures());
            appendFailures(message, "Errors", result.errors());
            throw new AssertionError(message.toString());
        }
    }

    private static void appendFailures(
            StringBuilder message,
            String label,
            Enumeration<? extends TestFailure> failures) {
        int count = 0;
        while (failures.hasMoreElements()) {
            if (count == 0) {
                message.append(label).append(":\n");
            }
            TestFailure failure = failures.nextElement();
            message.append("  - ")
                    .append(failure.failedTest())
                    .append(": ")
                    .append(failure.exceptionMessage())
                    .append('\n');
            count++;
            if (count >= 25) {
                message.append("  ... and more\n");
                break;
            }
        }
    }
}
