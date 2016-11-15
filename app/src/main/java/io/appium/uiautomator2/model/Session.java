package io.appium.uiautomator2.model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Session {
    public static final String SEND_KEYS_TO_ELEMENT = "sendKeysToElement";
    private String sessionId;
    private ConcurrentMap<String, JSONObject> commandConfiguration;
    private KnownElements knownElements;
    public static Map<String, Object> capabilities = new HashMap<String, Object>();

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


}
