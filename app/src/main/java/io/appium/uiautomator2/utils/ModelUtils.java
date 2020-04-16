package io.appium.uiautomator2.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.BaseModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class ModelUtils {
    public static <T extends BaseModel> T toModel(IHttpRequest request, Class<T> modelCls) {
        //noinspection unchecked
        return (T) new Gson().fromJson(request.body(), modelCls).validate();
    }

    public static Object toObject(JSONArray json, Type type) {
        return new Gson().fromJson(json.toString(), type);
    }

    public static Object toObject(JSONObject json, Type type) {
        return new Gson().fromJson(json.toString(), type);
    }

    public static String toJsonString(Object model) {
        return toJsonString(model, true);
    }

    public static String toJsonString(Object model, boolean includeNullValues) {
        GsonBuilder builder = new GsonBuilder()
                .disableHtmlEscaping();
        if (includeNullValues) {
            builder.serializeNulls();
        }
        return builder.create().toJson(model);
    }
}
