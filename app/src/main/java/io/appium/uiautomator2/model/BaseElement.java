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

import android.graphics.Rect;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import io.appium.uiautomator2.common.exceptions.NoSuchAttributeException;
import io.appium.uiautomator2.core.AxNodeInfoHelper;
import io.appium.uiautomator2.model.api.ElementModel;
import io.appium.uiautomator2.model.api.ElementRectModel;
import io.appium.uiautomator2.model.settings.ElementResponseAttributes;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.ShouldUseCompactResponses;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.core.AxNodeInfoExtractor.toAxNodeInfo;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public abstract class BaseElement implements AndroidElement {
    protected static final String ATTRIBUTE_PREFIX = "attribute/";

    private String id = UUID.randomUUID().toString();
    private final By by;
    private final String contextId;
    private final boolean isSingleMatch;

    public BaseElement(boolean isSingleMatch, By by, @Nullable String contextId) {
        this.by = by;
        this.contextId = contextId;
        this.isSingleMatch = isSingleMatch;
    }

    BaseElement withId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public void click() {
        AxNodeInfoHelper.click(toAxNodeInfo(getUiObject()));
    }

    @Override
    public void doubleClick() {
        AxNodeInfoHelper.doubleClick(toAxNodeInfo(getUiObject()));
    }

    @Override
    public void longClick() {
        AxNodeInfoHelper.longClick(toAxNodeInfo(getUiObject()));
    }

    @Override
    public void longClick(long durationMs) {
        AxNodeInfoHelper.longClick(toAxNodeInfo(getUiObject()), durationMs);
    }

    @Override
    public void drag(Point dest) {
        AxNodeInfoHelper.drag(toAxNodeInfo(getUiObject()), dest.toNativePoint());
    }

    @Override
    public void drag(Point dest, @Nullable Integer speed) {
        AxNodeInfoHelper.drag(toAxNodeInfo(getUiObject()), dest.toNativePoint(), speed);
    }

    @Override
    public void pinchClose(float percent) {
        AxNodeInfoHelper.pinchClose(toAxNodeInfo(getUiObject()), percent);
    }

    @Override
    public void pinchClose(float percent, @Nullable Integer speed) {
        AxNodeInfoHelper.pinchClose(toAxNodeInfo(getUiObject()), percent, speed);
    }

    @Override
    public void pinchOpen(float percent) {
        AxNodeInfoHelper.pinchOpen(toAxNodeInfo(getUiObject()), percent);
    }

    @Override
    public void pinchOpen(float percent, @Nullable Integer speed) {
        AxNodeInfoHelper.pinchOpen(toAxNodeInfo(getUiObject()), percent, speed);
    }

    @Override
    public void swipe(Direction direction, float percent) {
        AxNodeInfoHelper.swipe(toAxNodeInfo(getUiObject()), direction, percent);
    }

    @Override
    public void swipe(Direction direction, float percent, @Nullable Integer speed) {
        AxNodeInfoHelper.swipe(toAxNodeInfo(getUiObject()), direction, percent, speed);
    }

    @Override
    public boolean scroll(Direction direction, float percent) {
        return AxNodeInfoHelper.scroll(toAxNodeInfo(getUiObject()), direction, percent);
    }

    @Override
    public boolean scroll(Direction direction, float percent, @Nullable Integer speed) {
        return AxNodeInfoHelper.scroll(toAxNodeInfo(getUiObject()), direction, percent, speed);
    }

    @Override
    public boolean fling(Direction direction) {
        return AxNodeInfoHelper.fling(toAxNodeInfo(getUiObject()), direction);
    }

    @Override
    public boolean fling(Direction direction, @Nullable Integer speed) {
        return AxNodeInfoHelper.fling(toAxNodeInfo(getUiObject()), direction, speed);
    }

    @Override
    public String getText() {
        // By convention the text is replaced with an empty string if it equals to null
        return ElementHelpers.getText(getUiObject());
    }

    @Override
    public boolean setText(String text) {
        return ElementHelpers.setText(getUiObject(), text);
    }

    @Override
    public void setProgress(float value) {
        ElementHelpers.setProgress(getUiObject(), value);
    }

    @Override
    public boolean canSetProgress() {
        return ElementHelpers.canSetProgress(getUiObject());
    }

    @Override
    public Rect getBounds() {
        return AxNodeInfoHelper.getBounds(toAxNodeInfo(getUiObject()));
    }

    @Override
    public Point getAbsolutePosition(final Point offset) {
        final Rect bounds = this.getBounds();
        return PositionHelper.getAbsolutePosition(new Point(bounds.left, bounds.top), bounds, offset);
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getContextId() {
        return isBlank(contextId) ? null : contextId;
    }

    @Override
    public boolean isSingleMatch() {
        return isSingleMatch;
    }

    /**
     * Return the JSONObject which Appium returns for an element
     * <p>
     * For example, appium returns elements like [{"ELEMENT":1}, {"ELEMENT":2}]
     */
    @Override
    public Object toModel() throws UiObjectNotFoundException {
        ElementModel model = new ElementModel(this);
        if (Settings.get(ShouldUseCompactResponses.class).getValue()) {
            return model;
        }

        Map<String, Object> result = new HashMap<>(model.toMap());
        String [] responseAttributes = Settings.get(ElementResponseAttributes.class).asArray();
        for (String field : responseAttributes) {
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BaseElement)) {
            return false;
        }
        if (this == other) {
            return true;
        }

        BaseElement otherEl = (BaseElement)other;
        return Objects.equals(this.getUiObject(), otherEl.getUiObject())
                && Objects.equals(by, otherEl.by)
                && Objects.equals(contextId, otherEl.contextId)
                && this.isSingleMatch == otherEl.isSingleMatch;
    }
}
