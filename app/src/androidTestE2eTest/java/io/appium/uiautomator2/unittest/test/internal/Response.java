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
package io.appium.uiautomator2.unittest.test.internal;

import androidx.annotation.Nullable;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Response {

    private static final String ERR_MSG = "Unable to extract '%s' from body '%s'.";

    private final String body;
    private final int code;

    public Response(final com.squareup.okhttp.Response response) throws IOException {
        body = response.body().string();
        code = response.code();
    }

    public boolean isSuccessful() {
        return code == HttpResponseStatus.OK.code();
    }

    @Nullable
    public String getElementId() {
        try {
            return TestUtils.extractElementId(new JSONObject(body).getJSONObject("value"));
        } catch (JSONException e) {
            throw new IllegalArgumentException(String.format(ERR_MSG, "element", body), e);
        }
    }

    public int code() {
        return code;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        try {
            return (T) new JSONObject(body).get("value");
        } catch (JSONException e) {
            throw new IllegalArgumentException(String.format(ERR_MSG, "value", body), e);
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return String.format("Code:%d; Body:%s;", code, body);
    }
}
