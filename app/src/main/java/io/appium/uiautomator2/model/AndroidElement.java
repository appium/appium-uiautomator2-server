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

import java.util.List;

public interface AndroidElement {

    @Nullable By getBy();

    @Nullable String getContextId();

    boolean isSingleMatch();

    void clear() throws UiObjectNotFoundException;

    String getText();

    String getName() throws UiObjectNotFoundException;

    String getAttribute(String attr) throws UiObjectNotFoundException;

    boolean setText(final String text);

    boolean canSetProgress();

    void setProgress(final float value);

    String getId();

    Rect getBounds();

    AccessibleUiObject getChild(final Object sel) throws UiObjectNotFoundException;

    List<AccessibleUiObject> getChildren(final Object selector, final By by);

    String getContentDesc() throws UiObjectNotFoundException;

    Object getUiObject();

    Point getAbsolutePosition(final Point offset) throws UiObjectNotFoundException;

    Object toModel() throws UiObjectNotFoundException;

    //region Gestures
    /** Clicks on this object. */
    void click();

    /** Performs a double click on this object. */
    void doubleClick();

    /** Performs a long click on this object. */
    void longClick();

    /** Performs a click on this object that lasts for {@code durationMs} milliseconds. */
    void longClick(long durationMs);

    /**
     * Drags this object to the specified location.
     *
     * @param dest The end point that this object should be dragged to.
     */
    void drag(Point dest);

    /**
     * Drags this object to the specified location.
     *
     * @param dest The end point that this object should be dragged to.
     * @param speed The speed at which to perform this gesture in pixels per second.
     */
    void drag(Point dest, @Nullable Integer speed);

    /**
     * Performs a pinch close gesture on this object.
     *
     * @param percent The size of the pinch as a percentage of this object's size.
     */
    void pinchClose(float percent);

    /**
     * Performs a pinch close gesture on this object.
     *
     * @param percent The size of the pinch as a percentage of this object's size.
     * @param speed The speed at which to perform this gesture in pixels per second.
     */
    void pinchClose(float percent, @Nullable Integer speed);

    /**
     * Performs a pinch open gesture on this object.
     *
     * @param percent The size of the pinch as a percentage of this object's size.
     */
    void pinchOpen(float percent);

    /**
     * Performs a pinch open gesture on this object.
     *
     * @param percent The size of the pinch as a percentage of this object's size.
     * @param speed The speed at which to perform this gesture in pixels per second.
     */
    void pinchOpen(float percent, @Nullable Integer speed);

    /**
     * Performs a swipe gesture on this object.
     *
     * @param direction The direction in which to swipe.
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    void swipe(Direction direction, float percent);

    /**
     * Performs a swipe gesture on this object.
     *
     * @param direction The direction in which to swipe.
     * @param percent The length of the swipe as a percentage of this object's size.
     * @param speed The speed at which to perform this gesture in pixels per second.
     */
    void swipe(Direction direction, float percent, @Nullable Integer speed);

    /**
     * Performs a scroll gesture on this object.
     *
     * @param direction The direction in which to scroll.
     * @param percent The distance to scroll as a percentage of this object's visible size.
     * @return Whether the object can still scroll in the given direction.
     */
    boolean scroll(Direction direction, float percent);

    /**
     * Performs a scroll gesture on this object.
     *
     * @param direction The direction in which to scroll.
     * @param percent The distance to scroll as a percentage of this object's visible size.
     * @param speed The speed at which to perform this gesture in pixels per second.
     * @return Whether the object can still scroll in the given direction.
     */
    boolean scroll(Direction direction, float percent, @Nullable Integer speed);

    /**
     * Performs a fling gesture on this object.
     *
     * @param direction The direction in which to fling.
     * @return Whether the object can still scroll in the given direction.
     */
    boolean fling(Direction direction);

    /**
     * Performs a fling gesture on this object.
     *
     * @param direction The direction in which to fling.
     * @param speed The speed at which to perform this gesture in pixels per second.
     * @return Whether the object can still scroll in the given direction.
     */
    boolean fling(Direction direction, @Nullable Integer speed);
    // legacy
    boolean dragTo(final int destX, final int destY, final int steps) throws UiObjectNotFoundException;
    boolean dragTo(final Object destObj, final int steps) throws UiObjectNotFoundException;
    //endregion Gestures
}
