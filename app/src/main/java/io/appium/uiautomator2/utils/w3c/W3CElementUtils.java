package io.appium.uiautomator2.utils.w3c;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.model.AndroidElement;
import org.json.JSONException;
import org.json.JSONObject;

public class W3CElementUtils {
    public static final String JSONWP_ELEMENT_ID_KEY_NAME = "ELEMENT";
    public static final String W3C_ELEMENT_ID_KEY_NAME = "element-6066-11e4-a52e-4f735466cecf";

    @Nullable
    public static String extractElementId(JSONObject obj) {
        while (obj.keys().hasNext()) {
            String key = obj.keys().next();
            if ((key.equalsIgnoreCase(JSONWP_ELEMENT_ID_KEY_NAME) ||
                    key.equalsIgnoreCase(W3C_ELEMENT_ID_KEY_NAME))
                    && (obj.opt(key) instanceof String)) {
                return (String) obj.opt(key);
            }
        }
        return null;
    }

    public static void attachElementId(AndroidElement element, JSONObject destination)
            throws JSONException {
        destination.put(JSONWP_ELEMENT_ID_KEY_NAME, element.getId());
        destination.put(W3C_ELEMENT_ID_KEY_NAME, element.getId());
    }
}
