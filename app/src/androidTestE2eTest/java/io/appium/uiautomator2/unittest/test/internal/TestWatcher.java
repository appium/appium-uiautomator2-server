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
package io.appium.uiautomator2.unittest.test.internal;

import android.os.Environment;

import org.junit.runner.Description;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import io.appium.uiautomator2.utils.Device;

class TestWatcher extends org.junit.rules.TestWatcher {

    private static final String SCREENSHOT_FILE_EXTENSION = ".png";
    private static final String SCREENSHOT_FOLDER = "screenshots";

    private void saveScreenshot(final String fileName) {
        try {
            final File output = getFile(SCREENSHOT_FOLDER, fileName, SCREENSHOT_FILE_EXTENSION);
            Logger.debug("Taking screenshot:" + output.getAbsolutePath());
            Device.getUiDevice().takeScreenshot(output);
        } catch (Exception e) {
            Logger.error("Unable to take screenshot:" + e);
        }
    }

    private void dumpHierarchy() {
        Logger.debug("Dumping hierarchy");
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            Device.getUiDevice().dumpWindowHierarchy(stream);
            Logger.debug(stream.toString());
        } catch (Exception e) {
            Logger.error("Unable to dump hierarchy:" + e);
        }
    }

    private File getFile(final String folder, final String fileName, final String extension)
            throws IOException {
        final File file = new File(Environment.getExternalStorageDirectory(),
                folder + File.separator + fileName + extension);
        final File parentFile = file.getParentFile();
        if (!parentFile.mkdirs()) {
            throw new IOException("Can not create dirs:" + parentFile.getAbsolutePath());
        }
        if (file.exists() && !file.delete()) {
            throw new IOException("Can not delete existing file:" + file.getAbsolutePath());
        }
        return file;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        final String methodName = description.getMethodName();
        Logger.debug("FAILED:" + methodName);
        saveScreenshot(methodName);
        dumpHierarchy();
    }
}
