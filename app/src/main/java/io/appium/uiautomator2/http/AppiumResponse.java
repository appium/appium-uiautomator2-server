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

package io.appium.uiautomator2.http;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.JsonSyntaxException;
import io.appium.uiautomator2.model.api.server.ErrorModel;
import io.appium.uiautomator2.model.api.server.ResponseModel;

import java.nio.charset.StandardCharsets;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Logger;
import io.netty.handler.codec.http.HttpResponseStatus;

import static io.appium.uiautomator2.utils.ModelUtils.toJsonString;
import static io.appium.uiautomator2.utils.StringHelpers.abbreviate;

public class AppiumResponse {
    private final Object value;
    private final String sessionId;
    private final HttpResponseStatus httpStatus;

    public AppiumResponse(String sessionId, @Nullable Object value) {
        this.sessionId = sessionId;
        this.value = value;
        if (value instanceof Throwable) {
            this.httpStatus = (value instanceof UiAutomator2Exception)
                    ? ((UiAutomator2Exception) value).getHttpStatus()
                    : UiAutomator2Exception.DEFAULT_ERROR_STATUS;
        } else {
            this.httpStatus = HttpResponseStatus.OK;
        }
    }

    public AppiumResponse(String sessionId) {
        this(sessionId, null);
    }

    private static ErrorModel formatException(Throwable error) {
        UiAutomator2Exception err = (error instanceof UiAutomator2Exception)
                ? (UiAutomator2Exception) error
                : new UiAutomator2Exception(error);
        return new ErrorModel(
                err.getError(),
                err.getMessage(),
                Log.getStackTraceString(error)
        );
    }

    public void renderTo(IHttpResponse response) {
        response.setContentType("application/json");
        response.setEncoding(StandardCharsets.UTF_8);
        response.setStatus(getHttpStatus().code());
        try {
            Object val = (value instanceof Throwable) ? formatException((Throwable) value) : value;
            ResponseModel responseModel = new ResponseModel(val, sessionId);
            final String responseString = toJsonString(responseModel);
            Logger.info(String.format("AppiumResponse: %s", abbreviate(responseString, 300)));
            response.setContent(responseString);
        } catch (JsonSyntaxException e) {
            Logger.error("Unable to create JSON Object", e);
            response.setContent("{}");
            response.setStatus(UiAutomator2Exception.DEFAULT_ERROR_STATUS.code());
        }
    }

    public HttpResponseStatus getHttpStatus() {
        return httpStatus;
    }

    @Nullable
    public Object getValue() {
        return value;
    }
}

