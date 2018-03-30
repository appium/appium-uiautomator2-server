package io.appium.uiautomator2.model;

import org.apache.commons.lang.BooleanUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Session {
    public static final String SEND_KEYS_TO_ELEMENT = "sendKeysToElement";
    public static final String CAP_SHOULD_USE_COMPACT_RESPONSES = "shouldUseCompactResponses";
    public static final String CAP_ELEMENT_RESPONSE_FIELDS = "elementResponseFields";
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
        if (Session.capabilities.containsKey(CAP_SHOULD_USE_COMPACT_RESPONSES)) {
            shouldUseCompactResponses = BooleanUtils.toBoolean(Session.capabilities.get(CAP_SHOULD_USE_COMPACT_RESPONSES).toString());
        }
        return shouldUseCompactResponses;
    }

    public static String[] getElementResponseFields() {
        if (Session.capabilities.containsKey(CAP_ELEMENT_RESPONSE_FIELDS)) {
            return Session.capabilities.get(CAP_ELEMENT_RESPONSE_FIELDS).toString().split(",");
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
