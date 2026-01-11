package io.appium.uiautomator2.model.internal;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.COMPLEX_UNIT_IN;
import static android.util.TypedValue.COMPLEX_UNIT_MM;
import static android.util.TypedValue.COMPLEX_UNIT_PT;
import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static android.util.TypedValue.COMPLEX_UNIT_SP;
import static io.appium.uiautomator2.utils.DimensionsHelper.pxToDp;
import static io.appium.uiautomator2.utils.DimensionsHelper.pxToIn;
import static io.appium.uiautomator2.utils.DimensionsHelper.pxToMm;
import static io.appium.uiautomator2.utils.DimensionsHelper.pxToPt;
import static io.appium.uiautomator2.utils.DimensionsHelper.pxToSp;

public class TextData {

    public static TextData parseTextData(float textSizeInPx, int textSizeUnit) {
        switch (textSizeUnit) {
            case COMPLEX_UNIT_DIP: {
                return new TextData(pxToDp(textSizeInPx), "dp");
            }
            case COMPLEX_UNIT_SP: {
                return new TextData(pxToSp(textSizeInPx), "sp");
            }
            case COMPLEX_UNIT_PT: {
                return new TextData(pxToPt(textSizeInPx), "pt");
            }
            case COMPLEX_UNIT_IN: {
                return new TextData(pxToIn(textSizeInPx), "in");
            }
            case COMPLEX_UNIT_MM: {
                return new TextData(pxToMm(textSizeInPx), "mm");
            }
            case COMPLEX_UNIT_PX:
            default: {
                return new TextData(textSizeInPx, "px");
            }
        }
    }

    public final float textSize;

    public final String textUnit;

    public TextData(float textSize, String textUnit) {
        this.textSize = textSize;
        this.textUnit = textUnit;
    }
}
