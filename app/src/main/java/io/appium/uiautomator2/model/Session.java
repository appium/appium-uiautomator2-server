package io.appium.uiautomator2.model;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import androidx.annotation.Nullable;

import static io.appium.uiautomator2.model.settings.Settings.ELEMENT_RESPONSE_ATTRIBUTES;
import static io.appium.uiautomator2.model.settings.Settings.SHOULD_USE_COMPACT_RESPONSES;

public class Session {
    public static final String SEND_KEYS_TO_ELEMENT = "sendKeysToElement";
    public static final String NO_ID = "None";
    public final Map<String, Object> capabilities = new HashMap<>();
    private String sessionId;
    private ConcurrentMap<String, JSONObject> commandConfiguration;
    private KnownElements knownElements;
    private AccessibilityScrollData lastScrollData;

    Session(String sessionId, Map<String, Object> capabilities) {
        this.sessionId = sessionId;
        this.knownElements = new KnownElements();
        this.commandConfiguration = new ConcurrentHashMap<>();
        this.capabilities.putAll(capabilities);
        JSONObject configJsonObject = new JSONObject();
        this.commandConfiguration.put(SEND_KEYS_TO_ELEMENT, configJsonObject);
    }

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
        return getCapability(SHOULD_USE_COMPACT_RESPONSES.toString(), true);
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

    public void setCommandConfiguration(String command, JSONObject config) {
        if (commandConfiguration.containsKey(command)) {
            commandConfiguration.replace(command, config);
        }
    }

    public KnownElements getKnownElements() {
        return knownElements;
    }

    public JSONObject getCommandConfiguration(String command) {
        return commandConfiguration.get(command);
    }

    @Nullable
    public AccessibilityScrollData getLastScrollData() {
        return lastScrollData;
    }

    public void setLastScrollData(AccessibilityScrollData scrollData) {
        lastScrollData = scrollData;
    }
}
