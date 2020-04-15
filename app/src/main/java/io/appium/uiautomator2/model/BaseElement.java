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

package io.appium.uiautomator2.model;

import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.NoSuchAttributeException;
import io.appium.uiautomator2.model.api.ElementModel;
import io.appium.uiautomator2.model.api.ElementRectModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseElement implements AndroidElement {
    protected static final String ATTRIBUTE_PREFIX = "attribute/";

    /**
     * Return the JSONObject which Appium returns for an element
     * <p>
     * For example, appium returns elements like [{"ELEMENT":1}, {"ELEMENT":2}]
     */
    @Override
    public Object toModel() throws UiObjectNotFoundException {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        ElementModel model = new ElementModel(this);
        if (session.shouldUseCompactResponses()) {
            return model;
        }

        Map<String, Object> result = new HashMap<>(model.toMap());
        for (String field : session.getElementResponseAttributes()) {
            try {
                if (Objects.equals(field, "name")) {
                    result.put(field, this.getContentDesc());
                } else if (Objects.equals(field, "text")) {
                    result.put(field, this.getText());
                } else if (Objects.equals(field, "rect")) {
                    result.put(field, new ElementRectModel(this.getBounds()));
                } else if (Objects.equals(field, "enabled")
                        || Objects.equals(field, "displayed")
                        || Objects.equals(field, "selected")) {
                    result.put(field, this.getAttribute(field));
                } else if (field.startsWith(ATTRIBUTE_PREFIX)) {
                    String attributeName = field.substring(ATTRIBUTE_PREFIX.length());
                    result.put(field, this.getAttribute(attributeName));
                }
            } catch (NoSuchAttributeException e) {
                // ignore field
            }
        }
        return result;
    }

}
