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

package io.appium.uiautomator2.common.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

public class UiAutomator2Exception extends RuntimeException {
    public static final HttpResponseStatus DEFAULT_ERROR_STATUS = HttpResponseStatus.INTERNAL_SERVER_ERROR;

    public UiAutomator2Exception() {
        super("An unknown server-side error occurred while processing the command");
    }

    public UiAutomator2Exception(Throwable t) {
        super(t);
    }

    public UiAutomator2Exception(String message) {
        super(message);
    }

    public UiAutomator2Exception(String message, Throwable t) {
        super(message, t);
    }

    public String getError() {
        return "unknown error";
    }

    public HttpResponseStatus getHttpStatus() {
        return DEFAULT_ERROR_STATUS;
    }
}
