package io.appium.uiautomator2.utils;

import android.graphics.Rect;

import io.appium.uiautomator2.model.Point;
import org.junit.Test;
import org.mockito.Mockito;

import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PositionHelperTests {

    @Test
    public void zeroPointAndOffsets() throws InvalidCoordinatesException {
        Point zeroPoint = new Point();
        Point zeroOffset = new Point();

        Rect zeroRect = Mockito.mock(Rect.class);
        Mockito.when(zeroRect.width()).thenReturn(0);
        Mockito.when(zeroRect.height()).thenReturn(0);

        Point actualPoint = PositionHelper.getAbsolutePosition(zeroPoint, zeroRect, zeroOffset, false);

        assertThat(actualPoint.x, equalTo(0.0));
        assertThat(actualPoint.y, equalTo(0.0));
    }

    @Test
    public void zeroOnePointAndOneZeroOffsets() throws InvalidCoordinatesException {
        Point onePoint = new Point(0, 1);
        Point oneOffset = new Point(1, 0);

        Rect zeroRect = Mockito.mock(Rect.class);
        Mockito.when(zeroRect.width()).thenReturn(0);
        Mockito.when(zeroRect.height()).thenReturn(0);

        Point actualPoint = PositionHelper.getAbsolutePosition(onePoint, zeroRect, oneOffset, false);

        assertThat(actualPoint.x, equalTo(1.0));
        assertThat(actualPoint.y, equalTo(1.0));
    }

    @Test
    public void zeroPointAndOffsetsWithOneRect() throws InvalidCoordinatesException {
        Point onePoint = new Point(0, 1);
        Point oneOffset = new Point(1, 0);

        Rect oneRect = Mockito.mock(Rect.class);
        Mockito.when(oneRect.width()).thenReturn(1);
        Mockito.when(oneRect.height()).thenReturn(0);

        Point actualPoint = PositionHelper.getAbsolutePosition(onePoint, oneRect, oneOffset, false);

        assertThat(actualPoint.x, equalTo(1.0));
        assertThat(actualPoint.y, equalTo(1.0));
    }

    @Test
    public void zeroOnePointAndOneZeroOffsetsWithOneRect() throws InvalidCoordinatesException {
        Point onePoint = new Point(0, 1);
        Point oneOffset = new Point(1, 0);

        Rect oneRect = Mockito.mock(Rect.class);
        Mockito.when(oneRect.width()).thenReturn(1);
        Mockito.when(oneRect.height()).thenReturn(1);

        Point actualPoint = PositionHelper.getAbsolutePosition(onePoint, oneRect, oneOffset, false);

        assertThat(actualPoint.x, equalTo(1.0));
        assertThat(actualPoint.y, equalTo(1.0));
    }


    @Test(expected = InvalidCoordinatesException.class)
    public void onePointOverRect() throws InvalidCoordinatesException {
        Point onePoint = new Point(1, 1);
        Point oneOffset = new Point(1, 1);

        Rect zeroRect = Mockito.mock(Rect.class);
        Mockito.when(zeroRect.width()).thenReturn(0);
        Mockito.when(zeroRect.height()).thenReturn(0);

        PositionHelper.getAbsolutePosition(onePoint, zeroRect, oneOffset, true);
    }
}
