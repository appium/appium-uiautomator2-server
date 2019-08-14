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

public class ElementNotFoundException extends UiAutomator2Exception {
    public ElementNotFoundException() {
        super("An element could not be located on the page using the given search parameters");
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getError() {
        return "no such element";
    }

    @Override
    public HttpResponseStatus getHttpStatus() {
        return HttpResponseStatus.NOT_FOUND;
    }
}
