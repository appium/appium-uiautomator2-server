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
import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.List;

public interface AndroidElement {

    @Nullable By getBy();

    @Nullable String getContextId();

    boolean isSingleMatch();

    void clear() throws UiObjectNotFoundException;

    void click() throws UiObjectNotFoundException;

    boolean longClick() throws UiObjectNotFoundException;

    String getText();

    String getName() throws UiObjectNotFoundException;

    String getAttribute(String attr) throws UiObjectNotFoundException;

    boolean setText(final String text);

    boolean canSetProgress();

    void setProgress(final float value);

    String getId();

    Rect getBounds() throws UiObjectNotFoundException;

    Object getChild(final Object sel) throws UiObjectNotFoundException;

    List<?> getChildren(final Object selector, final By by) throws UiObjectNotFoundException;

    String getContentDesc() throws UiObjectNotFoundException;

    Object getUiObject();

    Point getAbsolutePosition(final Point offset) throws UiObjectNotFoundException;

    boolean dragTo(final int destX, final int destY, final int steps) throws UiObjectNotFoundException;

    boolean dragTo(final Object destObj, final int steps) throws UiObjectNotFoundException;

    Object toModel() throws UiObjectNotFoundException;
}
