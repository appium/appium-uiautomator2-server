package io.appium.uiautomator2.model.settings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AllowInvisibleElementsTest {

    private AllowInvisibleElements allowInvisibleElements;

    @Before
    public void setup() {
        allowInvisibleElements = new AllowInvisibleElements();
    }

    @Test
    public void shouldBeBoolean() {
        Assert.assertEquals(Boolean.class, allowInvisibleElements.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("allowInvisibleElements", allowInvisibleElements.getSettingName());
    }
}
