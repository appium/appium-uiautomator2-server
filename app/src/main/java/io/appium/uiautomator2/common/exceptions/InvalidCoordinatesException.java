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

@SuppressWarnings("serial")
public class InvalidCoordinatesException extends UiAutomator2Exception {
    /**
     * An exception that is thrown when an invalid coordinate is used.
     *
     * @param msg A descriptive message describing the error.
     */
    public InvalidCoordinatesException(final String msg) {
        super(msg);
    }
}
