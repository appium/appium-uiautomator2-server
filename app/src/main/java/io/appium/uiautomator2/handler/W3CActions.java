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

package io.appium.uiautomator2.handler;

import android.view.KeyEvent;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.touch.w3c.W3CActionsModel;
import io.appium.uiautomator2.model.api.touch.w3c.W3CItemModel;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.w3c.ActionTokens;
import io.appium.uiautomator2.utils.w3c.ActionsExecutor;
import io.appium.uiautomator2.utils.w3c.ActionsParseException;
import io.appium.uiautomator2.utils.w3c.ActionsPreprocessor;
import io.appium.uiautomator2.utils.w3c.ActionsTokenizer;

import java.util.List;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class W3CActions extends SafeRequestHandler {
    private static final ActionsPreprocessor actionsPreprocessor = new ActionsPreprocessor();
    private static final ActionsTokenizer actionsTokenizer = new ActionsTokenizer();

    public W3CActions(String mappedUri) {
        super(mappedUri);
    }

    /**
     * Android handler for <a href="https://github.com/jlipps/simple-wd-spec#perform-actions">W3C actions endpoint</a>
     * <p>
     * All input source types are supported as well as multi-touch gestures.
     * <p>
     * The following additional item options are supported for <b>pointer</b> source:
     * <ul>
     * <li>pressure - A value in range [0.0, 1.0], which defines pointer pressure,
     * where 1.0 is the normal pressure (the default value) and 0.0 means no pressure.</li>
     * <li>size - A normalized value that describes the approximate size of the pointer touch area
     * in relation to the maximum detectable size of the device.
     * It represents some approximation of the area of the screen being
     * pressed; the actual value in pixels corresponding to the
     * touch is normalized with the device specific range of values
     * and scaled to a value between 0 and 1 (the default value).
     * The value of size can be used to determine fat touch events.</li>
     * </ul>
     * <p>
     * Applicable key and meta key codes for <b>key</b> input source can be found in
     * {@link KeyEvent} documentation. Value transformation to a numeric key code is
     * done via {@link String#charAt(int)} method call, which means, for example,
     * that the value <em>"\\u2000"</em> equals to meta key code 0x2000
     * {@link KeyEvent#META_CTRL_LEFT_ON}. Meta key codes are also applied to pointer
     * events, which are happening at the same moment.
     * <p>
     *
     * @param request JSON request formatted according to W3C actions endpoint compilation rules.
     * @return The standard {@link AppiumResponse} instance with return value or error code inside.
     */
    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        try {
            final List<W3CItemModel> actions = actionsPreprocessor.preprocess(
                    toModel(request, W3CActionsModel.class).actions
            );

            final ActionTokens actionTokens = actionsTokenizer.tokenize(actions);
            if (new ActionsExecutor(actionTokens).execute()) {
                return new AppiumResponse(getSessionId(request));
            }
            throw new InvalidElementStateException(
                    "Unable to perform W3C actions. Check the logcat output " +
                            "for possible error reports and make sure your input actions chain is valid.");
        } catch (ActionsParseException e) {
            Logger.error("Exception while reading JSON: ", e);
            throw new InvalidArgumentException("Exception while reading JSON", e);
        }
    }
}
