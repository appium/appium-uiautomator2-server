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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServerConfig {
    // server port (default: `6790`)
    private final static int SERVER_PORT =
        System.getenv("SERVER_PORT") == null ? 6790 :
            Integer.parseInt(System.getenv("SERVER_PORT"));
    // mjpeg server port (default: `7810`)
    private final static int MJPEG_SERVER_PORT =
        System.getenv("MJPEG_SERVER_PORT") == null ? 7810 :
            Integer.parseInt(System.getenv("MJPEG_SERVER_PORT"));
    // mjpeg stream will try to match this framerate (default: `10`)
    private final static int MJPEG_SERVER_FRAMERATE =
        System.getenv("MJPEG_SERVER_FRAMERATE") == null ? 10 :
            Integer.parseInt(System.getenv("MJPEG_SERVER_FRAMERATE"));
    // mjpeg scale should be between 1 and 100 (default: `50`)
    private final static int MJPEG_SCALING_FACTOR =
        System.getenv("MJPEG_SCALING_FACTOR") == null ? 50 :
            Integer.parseInt(System.getenv("MJPEG_SCALING_FACTOR"));
    // mjpeg quality is between 0 and 100 (default: `75`)
    private final static int MJPEG_SERVER_SCREENSHOT_QUALITY =
        System.getenv("MJPEG_SERVER_SCREENSHOT_QUALITY") == null ? 75 :
            Integer.parseInt(System.getenv("MJPEG_SERVER_SCREENSHOT_QUALITY"));
    // if filtering should be used in mjpeg resize operation (default: `false`)
    private final static boolean MJPEG_FILTERING =
        System.getenv("MJPEG_FILTERING") == null ? false :
            System.getenv("MJPEG_FILTERING") == "true";

    // In-memory overrides
    private static ConcurrentMap<String, Object> overrides =
        new ConcurrentHashMap<>();

    /* Getter(s) */

    public static int getServerPort() {
        if (overrides.containsKey("serverPort")) {
            return (int) overrides.get("serverPort");
        }
        return SERVER_PORT;
    }

    public static int getMjpegServerPort() {
        if (overrides.containsKey("mjpegServerPort")) {
            return (int) overrides.get("mjpegServerPort");
        }
        return MJPEG_SERVER_PORT;
    }

    public static int getMjpegServerFramerate() {
        if (overrides.containsKey("mjpegServerFramerate")) {
            return (int) overrides.get("mjpegServerFramerate");
        }
        return MJPEG_SERVER_FRAMERATE;
    }

    public static int getMjpegServerScreenshotQuality() {
        if (overrides.containsKey("mjpegServerScreenshotQuality")) {
            return (int) overrides.get("mjpegServerScreenshotQuality");
        }
        return MJPEG_SERVER_SCREENSHOT_QUALITY;
    }

    public static int getMjpegScalingFactor() {
        if (overrides.containsKey("mjpegScalingFactor")) {
            return (int) overrides.get("mjpegScalingFactor");
        }
        return MJPEG_SCALING_FACTOR;
    }

    public static boolean getMjpegFiltering() {
        if (overrides.containsKey("mjpegFiltering")) {
            return (boolean) overrides.get("mjpegFiltering");
        }
        return MJPEG_FILTERING;
    }

    /* Setter(s) */

    public static void setServerPort(int serverPort) {
        overrides.put("serverPort", serverPort);
    }

    public static void setMjpegServerPort(int mjpegServerPort) {
        overrides.put("mjpegServerPort", mjpegServerPort);
    }

    public static void setMjpegServerFramerate(int mjpegServerFramerate) {
        overrides.put("mjpegServerFramerate", mjpegServerFramerate);
    }

    public static void setMjpegServerScreenshotQuality(int mjpegServerScreenshotQuality) {
        overrides.put("mjpegServerScreenshotQuality", mjpegServerScreenshotQuality);
    }

    public static void setMjpegScalingFactor(int mjpegScalingFactor) {
        overrides.put("mjpegScalingFactor", mjpegScalingFactor);
    }

    public static void setMjpegFiltering(boolean mjpegFiltering) {
        overrides.put("mjpegFiltering", mjpegFiltering);
    }
}
