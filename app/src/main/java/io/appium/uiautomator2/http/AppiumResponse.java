package io.appium.uiautomator2.http;

import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Logger;
import io.netty.handler.codec.http.HttpResponseStatus;

import static io.appium.uiautomator2.utils.JSONUtils.formatNull;

public class AppiumResponse {
    private final Object value;
    private final String sessionId;
    private HttpResponseStatus status = HttpResponseStatus.OK;

    public AppiumResponse(String sessionId, @Nullable Object value) {
        this.sessionId = sessionId;
        this.value = value;
    }

    public AppiumResponse(String sessionId) {
        this(sessionId, null);
    }

    private static Map<String, Object> formatException(Throwable error) {
        UiAutomator2Exception err = (error instanceof UiAutomator2Exception)
                ? (UiAutomator2Exception) error
                : new UiAutomator2Exception(error);
        Map<String, Object> result = new HashMap<>();
        result.put("error", err.getError());
        result.put("message", err.getMessage());
        result.put("stacktrace", Log.getStackTraceString(error));
        return result;
    }

    public void renderTo(IHttpResponse response) {
        response.setContentType("application/json");
        response.setEncoding(StandardCharsets.UTF_8);
        final boolean isError = value instanceof Throwable;
        JSONObject o = new JSONObject();
        try {
            o.put("sessionId", formatNull(sessionId));
            o.put("value", isError ? formatException((Throwable) value) : formatNull(value));
            final String responseString = o.toString();
            Logger.info(String.format("AppiumResponse: %s", responseString));
            response.setContent(responseString);
            if (isError) {
                response.setStatus((value instanceof UiAutomator2Exception)
                        ? ((UiAutomator2Exception) value).getHttpStatus().code()
                        : UiAutomator2Exception.DEFAULT_ERROR_STATUS.code());
            }
        } catch (JSONException e) {
            Logger.error("Unable to create JSON Object:", e);
            response.setContent("{}");
            response.setStatus(UiAutomator2Exception.DEFAULT_ERROR_STATUS.code());
        }
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public Object getValue() {
        return value;
    }
}

