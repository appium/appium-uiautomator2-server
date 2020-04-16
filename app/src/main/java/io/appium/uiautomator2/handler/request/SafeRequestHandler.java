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

package io.appium.uiautomator2.handler.request;

import com.google.gson.JsonSyntaxException;

import androidx.test.uiautomator.StaleObjectException;
import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.common.exceptions.StaleElementReferenceException;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.Logger;

public abstract class SafeRequestHandler extends BaseRequestHandler {

    public SafeRequestHandler(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public final AppiumResponse handle(IHttpRequest request) {
        Logger.info(String.format("%s command", getClass().getSimpleName()));
        try {
            return safeHandle(request);
        } catch (UiObjectNotFoundException e) {
            return new AppiumResponse(getSessionId(request), new ElementNotFoundException(e));
        } catch (StaleObjectException e) {
            return new AppiumResponse(getSessionId(request), new StaleElementReferenceException(e));
        } catch (JsonSyntaxException | IllegalArgumentException e) {
            return new AppiumResponse(getSessionId(request), new InvalidArgumentException(e));
        } catch (Throwable e) {
            // Catching Errors seems like a bad idea in general but if we don't catch this,
            // Netty will catch it anyway.
            // The advantage of catching it here is that we can propagate the Error to clients.
            return new AppiumResponse(getSessionId(request), e);
        }
    }
}
