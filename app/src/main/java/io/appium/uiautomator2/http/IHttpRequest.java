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

import java.util.Map;

public interface IHttpRequest {
    /**
     * Returns "GET", "POST", "PUT" or "DELETE".
     */
    String method();

    /**
     * Returns the request URI.
     */
    String uri();

    /**
     * Returns the full request body.
     */
    String body();

    /**
     * Gets the value of a given header.
     */
    String header(String name);

    /**
     * Returns additional data appended to the request.
     */
    Map<String, Object> data();
}
