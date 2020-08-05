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

public class UnknownCommandException extends UiAutomator2Exception {
    public UnknownCommandException() {
        super("The requested resource could not be found, or a request was received " +
                "using an HTTP method that is not supported by the mapped resource");
    }

    public UnknownCommandException(String message) {
        super(message);
    }

    @Override
    public String getError() {
        return "unknown command";
    }

    @Override
    public HttpResponseStatus getHttpStatus() {
        return HttpResponseStatus.NOT_FOUND;
    }
}
