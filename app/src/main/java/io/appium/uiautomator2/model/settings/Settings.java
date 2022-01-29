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

package io.appium.uiautomator2.model.settings;

public enum Settings {
    ACTION_ACKNOWLEDGMENT_TIMEOUT(new ActionAcknowledgmentTimeout()),
    ALLOW_INVISIBLE_ELEMENTS(new AllowInvisibleElements()),
    COMPRESSED_LAYOUT_HIERARCHY(new CompressedLayoutHierarchy()),
    ELEMENT_RESPONSE_ATTRIBUTES(new ElementResponseAttributes()),
    ENABLE_MULTI_WINDOWS(new EnableMultiWindows()),
    ENABLE_NOTIFICATION_LISTENER(new EnableNotificationListener()),
    KEY_INJECTION_DELAY(new KeyInjectionDelay()),
    SCROLL_ACKNOWLEDGMENT_TIMEOUT(new ScrollAcknowledgmentTimeout()),
    SHOULD_USE_COMPACT_RESPONSES(new ShouldUseCompactResponses()),
    WAIT_FOR_IDLE_TIMEOUT(new WaitForIdleTimeout()),
    WAIT_FOR_SELECTOR_TIMEOUT(new WaitForSelectorTimeout()),
    NORMALIZE_TAG_NAMES(new NormalizeTagNames()),
    ENFORCE_XPATH1(new EnforceXpath1()),
    LIMIT_XPATH_CONTEXT_SCOPE(new LimitXpathContextScope()),
    SHUTDOWN_ON_POWER_DISCONNECT(new ShutdownOnPowerDisconnect()),
    SIMPLE_BOUNDS_CALCULATION(new SimpleBoundsCalculation()),
    TRACK_SCROLL_EVENTS(new TrackScrollEvents()),
    WAKE_LOCK_TIMEOUT(new WakeLockTimeout()),
    SERVER_PORT(new ServerPort()),
    MJPEG_SERVER_PORT(new MjpegServerPort()),
    MJPEG_SERVER_FRAMERATE(new MjpegServerFramerate()),
    MJPEG_SCALING_FACTOR(new MjpegScalingFactor()),
    MJPEG_SERVER_SCREENSHOT_QUALITY(new MjpegServerScreenshotQuality()),
    MJPEG_BILINEAR_FILTERING(new MjpegBilinearFiltering()),
    USE_RESOURCES_FOR_ORIENTATION_DETECTION(new UseResourcesForOrientationDetection());

    private final ISetting<?> setting;

    Settings(ISetting<?> setting) {
        this.setting = setting;
    }

    public ISetting<?> getSetting() {
        return setting;
    }

    public static <T extends ISetting<?>> T get(Class<T> settingType) {
        for (Settings enumItem: values()) {
            if (enumItem.getSetting().getClass() == settingType) {
                return settingType.cast(enumItem.getSetting());
            }
        }
        throw new IllegalArgumentException(String.format("%s setting is not known",
                settingType.getCanonicalName()));
    }

    @Override
    public String toString() {
        return setting.getName();
    }
}
