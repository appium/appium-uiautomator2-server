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

package io.appium.uiautomator2.utils;

import android.graphics.Rect;

import androidx.test.uiautomator.UiDevice;

import io.appium.uiautomator2.model.Point;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static io.appium.uiautomator2.model.Point.ZERO_POINT;

public abstract class PositionHelper {

    /**
     * Given a position, it will return either the position based on percentage (by passing in a
     * double between 0 and 1) or absolute position based on the coordinates entered.
     *
     * @param pointCoord The position to translate.
     * @param length     Length of side to use for percentage positions.
     * @param offset     Position offset.
     */
    private static double translateCoordinate(double pointCoord, double length, double offset) {
        double translatedCoord = Math.abs(pointCoord) > 0 && Math.abs(pointCoord) < 1
            ? length * pointCoord
            : pointCoord;
        return translatedCoord + offset;
    }

    /**
     * Translates coordinates relative to an element rectangle into absolute coordinates.
     *
     * @param point             A point in relative coordinates.
     * @param displayRect       The display rectangle to which the point is relative.
     * @param offsets           X and Y values by which to offset the point. These are typically the
     *                          absolute coordinates of the display rectangle.
     */
    public static Point getAbsolutePosition(Point point, Rect displayRect, Point offsets) {
        final Point absolutePosition = new Point(
                translateCoordinate(point.x, displayRect.width(), offsets.x),
                translateCoordinate(point.y, displayRect.height(), offsets.y)
        );
        if (!displayRect.contains(absolutePosition.x.intValue(), absolutePosition.y.intValue())) {
            Logger.warn(String.format(
                    "Coordinate %s is outside of the display rect %s. Continuing anyway",
                    absolutePosition, displayRect.toShortString())
            );
        }
        return absolutePosition;
    }

    public static Point getDeviceAbsPos(final Point point) {
        final UiDevice d = UiDevice.getInstance(getInstrumentation());
        final Rect displayRect = new Rect(0, 0, d.getDisplayWidth(), d.getDisplayHeight());
        Logger.debug("Display bounds: " + displayRect.toShortString());
        return getAbsolutePosition(point, displayRect, ZERO_POINT);
    }
}
