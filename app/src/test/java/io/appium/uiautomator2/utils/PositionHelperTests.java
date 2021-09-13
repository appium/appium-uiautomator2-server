package io.appium.uiautomator2.utils;

import android.graphics.Rect;

import org.junit.Test;
import org.mockito.Mockito;

import io.appium.uiautomator2.model.Point;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PositionHelperTests {

    @Test
    public void zeroPointAndOffsets() {
        Point zeroPoint = new Point();
        Point zeroOffset = new Point();

        Rect zeroRect = Mockito.mock(Rect.class);
        Mockito.when(zeroRect.width()).thenReturn(0);
        Mockito.when(zeroRect.height()).thenReturn(0);

        Point actualPoint = PositionHelper.getAbsolutePosition(zeroPoint, zeroRect, zeroOffset);

        assertThat(actualPoint.x, equalTo(0.0));
        assertThat(actualPoint.y, equalTo(0.0));
    }

    @Test
    public void zeroOnePointAndOneZeroOffsets() {
        Point onePoint = new Point(0, 1);
        Point oneOffset = new Point(1, 0);

        Rect zeroRect = Mockito.mock(Rect.class);
        Mockito.when(zeroRect.width()).thenReturn(0);
        Mockito.when(zeroRect.height()).thenReturn(0);

        Point actualPoint = PositionHelper.getAbsolutePosition(onePoint, zeroRect, oneOffset);

        assertThat(actualPoint.x, equalTo(1.0));
        assertThat(actualPoint.y, equalTo(1.0));
    }

    @Test
    public void zeroPointAndOffsetsWithOneRect() {
        Point onePoint = new Point(0, 1);
        Point oneOffset = new Point(1, 0);

        Rect oneRect = Mockito.mock(Rect.class);
        Mockito.when(oneRect.width()).thenReturn(1);
        Mockito.when(oneRect.height()).thenReturn(0);

        Point actualPoint = PositionHelper.getAbsolutePosition(onePoint, oneRect, oneOffset);

        assertThat(actualPoint.x, equalTo(1.0));
        assertThat(actualPoint.y, equalTo(1.0));
    }

    @Test
    public void zeroOnePointAndOneZeroOffsetsWithOneRect() {
        Point onePoint = new Point(0, 1);
        Point oneOffset = new Point(1, 0);

        Rect oneRect = Mockito.mock(Rect.class);
        Mockito.when(oneRect.width()).thenReturn(1);
        Mockito.when(oneRect.height()).thenReturn(1);

        Point actualPoint = PositionHelper.getAbsolutePosition(onePoint, oneRect, oneOffset);

        assertThat(actualPoint.x, equalTo(1.0));
        assertThat(actualPoint.y, equalTo(1.0));
    }
}
