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

package io.appium.uiautomator2.server;

import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.model.settings.MjpegBilinearFiltering;
import io.appium.uiautomator2.model.settings.MjpegScalingFactor;
import io.appium.uiautomator2.model.settings.MjpegServerFramerate;
import io.appium.uiautomator2.model.settings.MjpegServerPort;
import io.appium.uiautomator2.model.settings.MjpegServerScreenshotQuality;
import io.appium.uiautomator2.model.settings.ServerPort;

public class ServerConfig {
    public static final int DEFAULT_SERVER_PORT = 6790;
    public static final int DEFAULT_MJPEG_SERVER_PORT = 7810;
    public static final int DEFAULT_MJPEG_SERVER_FRAMERATE = 10;
    public static final int DEFAULT_MJPEG_SCALING_FACTOR = 50;
    public static final int DEFAULT_MJPEG_SERVER_SCREENSHOT_QUALITY = 50;
    public static final boolean DEFAULT_MJPEG_SERVER_BILINEAR_FILTERING = false;

    private final static int SERVER_PORT = getValueFromEnvOrDefault(
        "SERVER_PORT",
        DEFAULT_SERVER_PORT);
    private final static int MJPEG_SERVER_PORT = getValueFromEnvOrDefault(
        "MJPEG_SERVER_PORT",
        DEFAULT_MJPEG_SERVER_PORT);
    private final static int MJPEG_SERVER_FRAMERATE = getValueFromEnvOrDefault(
        "MJPEG_SERVER_FRAMERATE",
        DEFAULT_MJPEG_SERVER_FRAMERATE);
    private final static int MJPEG_SCALING_FACTOR = getValueFromEnvOrDefault(
        "MJPEG_SCALING_FACTOR",
        DEFAULT_MJPEG_SCALING_FACTOR);
    private final static int MJPEG_SERVER_SCREENSHOT_QUALITY = getValueFromEnvOrDefault(
        "MJPEG_SERVER_SCREENSHOT_QUALITY",
        DEFAULT_MJPEG_SERVER_SCREENSHOT_QUALITY);
    private final static boolean MJPEG_BILINEAR_FILTERING =
        Boolean.parseBoolean(System.getenv("MJPEG_BILINEAR_FILTERING"));

    // In-memory overrides
    private static Map<String, Object> overrides = new HashMap<>();

    private static int getValueFromEnvOrDefault(String key, int defaultValue) {
        return System.getenv(key) != null ?
            Integer.parseInt(System.getenv(key)) :
            defaultValue;
    }

    private static <T> T getValueFromOverridesOrDefault(String key, T defaultValue) {
        synchronized (overrides) {
            return overrides.containsKey(key) ?
                ((T) overrides.get(key)) :
                defaultValue;
        }
    }

    private static void setOverridesValue(String key, Object value) {
        synchronized (overrides) {
            overrides.put(key, value);
        }
    }

    public static int getServerPort() {
        return getValueFromOverridesOrDefault(
            ServerPort.SETTING_NAME,
            SERVER_PORT);
    }

    public static int getMjpegServerPort() {
        return getValueFromOverridesOrDefault(
            MjpegServerPort.SETTING_NAME,
            MJPEG_SERVER_PORT);
    }

    public static int getMjpegServerFramerate() {
        return getValueFromOverridesOrDefault(
            MjpegServerFramerate.SETTING_NAME,
            MJPEG_SERVER_FRAMERATE);
    }

    public static int getMjpegServerScreenshotQuality() {
        return getValueFromOverridesOrDefault(
            MjpegServerScreenshotQuality.SETTING_NAME,
            MJPEG_SERVER_SCREENSHOT_QUALITY);
    }

    public static int getMjpegScalingFactor() {
        return getValueFromOverridesOrDefault(
            MjpegScalingFactor.SETTING_NAME,
            MJPEG_SCALING_FACTOR);
    }

    public static boolean isMjpegBilinearFiltering() {
        return getValueFromOverridesOrDefault(
            MjpegBilinearFiltering.SETTING_NAME,
            MJPEG_BILINEAR_FILTERING);
    }

    public static void setServerPort(int serverPort) {
        setOverridesValue(ServerPort.SETTING_NAME, serverPort);
    }

    public static void setMjpegServerPort(int mjpegServerPort) {
        setOverridesValue(MjpegServerPort.SETTING_NAME, mjpegServerPort);
    }

    public static void setMjpegServerFramerate(int mjpegServerFramerate) {
        setOverridesValue(
            MjpegServerFramerate.SETTING_NAME,
            mjpegServerFramerate);
    }

    public static void setMjpegServerScreenshotQuality(int mjpegServerScreenshotQuality) {
        setOverridesValue(
            MjpegServerScreenshotQuality.SETTING_NAME,
            mjpegServerScreenshotQuality);
    }

    public static void setMjpegScalingFactor(int mjpegScalingFactor) {
        setOverridesValue(MjpegScalingFactor.SETTING_NAME, mjpegScalingFactor);
    }

    public static void setMjpegBilinearFiltering(boolean mjpegBilinearFiltering) {
        setOverridesValue(
            MjpegBilinearFiltering.SETTING_NAME,
            mjpegBilinearFiltering);
    }
}
