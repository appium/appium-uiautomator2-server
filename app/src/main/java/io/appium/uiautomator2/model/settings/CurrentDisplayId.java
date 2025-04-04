package io.appium.uiautomator2.model.settings;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Service;
import android.hardware.display.DisplayManager;
import android.view.Display;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;

public class CurrentDisplayId extends AbstractSetting<Integer> {
    private static final String SETTING_NAME = "currentDisplayId";
    private static final int DEFAULT_VALUE = Display.DEFAULT_DISPLAY;
    private int value = DEFAULT_VALUE;

    public CurrentDisplayId() {
        super(Integer.class, SETTING_NAME);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    protected void apply(Integer currentDisplayId) {
        DisplayManager displayManager = (DisplayManager) getInstrumentation().getContext().getSystemService(Service.DISPLAY_SERVICE);
        List<Integer> displayIds = Arrays.stream(displayManager.getDisplays()).map(Display::getDisplayId).collect(Collectors.toList());

        if (!displayIds.contains(currentDisplayId)) {
            String possibleValuesMessage = displayIds.stream().map(String::valueOf).collect(Collectors.joining(","));
            throw new InvalidArgumentException(String.format(
                    "Invalid %s value specified, must be one of %s. %s was given",
                    SETTING_NAME,
                    possibleValuesMessage,
                    currentDisplayId
            ));
        }

        value = currentDisplayId;
    }
}
