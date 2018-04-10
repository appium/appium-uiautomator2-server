package io.appium.uiautomator2.model;

import org.apache.commons.lang.BooleanUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static io.appium.uiautomator2.model.settings.Settings.ELEMENT_RESPONSE_ATTRIBUTES;
import static io.appium.uiautomator2.model.settings.Settings.SHOULD_USE_COMPACT_RESPONSES;

public class Session {
    public static final String SEND_KEYS_TO_ELEMENT = "sendKeysToElement";
    private String sessionId;
    private ConcurrentMap<String, JSONObject> commandConfiguration;
    private KnownElements knownElements;
    private AccessibilityScrollData lastScrollData;
    public static Map<String, Object> capabilities = new HashMap<>();

    public Session(String sessionId) {
        this.sessionId = sessionId;
        this.knownElements = new KnownElements();
        this.commandConfiguration = new ConcurrentHashMap<>();
        JSONObject configJsonObject = new JSONObject();
        this.commandConfiguration.put(SEND_KEYS_TO_ELEMENT, configJsonObject);
        NotificationListener.getInstance().start();
    }

    public String getSessionId() {
        return sessionId;
    }

    public static boolean shouldUseCompactResponses() {
        boolean shouldUseCompactResponses = true;
        if (Session.capabilities.containsKey(SHOULD_USE_COMPACT_RESPONSES.toString())) {
            shouldUseCompactResponses = BooleanUtils.toBoolean(
                    Session.capabilities.get(SHOULD_USE_COMPACT_RESPONSES.toString()).toString());
        }
        return shouldUseCompactResponses;
    }

    public static String[] getElementResponseAttributes() {
        if (Session.capabilities.containsKey(ELEMENT_RESPONSE_ATTRIBUTES.toString())) {
            return Session.capabilities.get(ELEMENT_RESPONSE_ATTRIBUTES.toString()).toString().
                    split(",");
        }
        return new String[] { "name", "text" };
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

    public void setLastScrollData(AccessibilityScrollData scrollData) {
        lastScrollData = scrollData;
    }

    public AccessibilityScrollData getLastScrollData() {
        return lastScrollData;
    }
}
