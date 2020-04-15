package io.appium.uiautomator2.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.BaseModel;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

public class ModelUtils {
    public static <T extends BaseModel> T toModel(IHttpRequest request, Class<T> modelCls) {
        return toModel(request.body(), modelCls);
    }

    public static Map<String, Object> toMap(JSONObject json) {
        //noinspection unchecked
        return (Map<String, Object>) toObject(json, new TypeToken<Map<String, Object>>() { }.getType());
    }

    public static Object toObject(JSONObject json, Type type) {
        return new Gson().fromJson(json.toString(), type);
    }

    public static <T extends BaseModel> T toModel(String jsonStr, Class<T> modelCls) {
        return new Gson().fromJson(jsonStr, modelCls);
    }

    public static String toJsonString(Object model) {
        return toJsonString(model, true);
    }

    public static String toJsonString(Object model, boolean includeNullValues) {
        GsonBuilder builder = new GsonBuilder();
        if (includeNullValues) {
            builder.serializeNulls();
        }
        return builder.create().toJson(model);
    }
}
