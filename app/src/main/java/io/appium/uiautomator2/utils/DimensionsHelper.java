package io.appium.uiautomator2.utils;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DimensionsHelper {

    private static final float POINTS_PER_INCH = 72f;
    private static final float MM_PER_INCH = 25.4f;

    public static float pxToDp(float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float pxToSp(float px) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            return TypedValue.deriveDimension(COMPLEX_UNIT_SP, px, Resources.getSystem().getDisplayMetrics());
        }
        return px / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static float pxToPt(float px) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return px * POINTS_PER_INCH / dm.xdpi;
    }

    public static float pxToIn(float px) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return px / dm.xdpi;
    }

    public static float pxToMm(float px) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return px * MM_PER_INCH / dm.xdpi;
    }
}
