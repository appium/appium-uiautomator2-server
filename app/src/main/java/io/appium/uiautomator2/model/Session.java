package io.appium.uiautomator2.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

import static io.appium.uiautomator2.model.settings.Settings.ELEMENT_RESPONSE_ATTRIBUTES;
import static io.appium.uiautomator2.model.settings.Settings.SHOULD_USE_COMPACT_RESPONSES;

public class Session {
    public static final String NO_ID = "None";
    public final Map<String, Object> capabilities = new HashMap<>();
    private final String sessionId;
    private final KnownElements knownElements = new KnownElements();
    private AccessibilityScrollData lastScrollData;

    Session(String sessionId, Map<String, Object> capabilities) {
        this.sessionId = sessionId;
        this.capabilities.putAll(capabilities);
    }

    @SuppressWarnings("UnusedReturnValue")
    public <T> T setCapability(String name, T value) {
        capabilities.put(name, value);
        return value;
    }

    @Nullable
    public Object getCapability(String name) {
        return capabilities.get(name);
    }

    public <T> T getCapability(String name, T defaultValue) {
        //noinspection unchecked
        return hasCapability(name) ? (T) capabilities.get(name) : defaultValue;
    }

    public Map<String, Object> getCapabilities() {
        return Collections.unmodifiableMap(capabilities);
    }

    public boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }

    public boolean shouldUseCompactResponses() {
        String capName = SHOULD_USE_COMPACT_RESPONSES.toString();
        return !hasCapability(capName) || String.valueOf(getCapability(capName)).equals("true");
    }

    public String[] getElementResponseAttributes() {
        String capName = ELEMENT_RESPONSE_ATTRIBUTES.toString();
        return getCapability(capName, "").trim().isEmpty()
                ? new String[]{"name", "text"}
                : getCapability(capName, "").split(",");
    }

    public String getSessionId() {
        return sessionId;
    }

    @Nullable
    public AccessibilityScrollData getLastScrollData() {
        return lastScrollData;
    }

    public void setLastScrollData(AccessibilityScrollData scrollData) {
        lastScrollData = scrollData;
    }

    public KnownElements getKnownElements() {
        return this.knownElements;
    }
}
