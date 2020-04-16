package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.model.api.ScrollToModel;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.internal.NativeAndroidBySelector;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_ACCESSIBILITY_ID;
import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_ANDROID_UIAUTOMATOR;
import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_CLASS;
import static io.appium.uiautomator2.utils.Device.scrollToElement;

import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelector;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class ScrollTo extends SafeRequestHandler {

    public ScrollTo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        ScrollToModel model = toModel(request, ScrollToModel.class);
        String strategy = model.params.strategy;
        String selector = model.params.selector;
        int maxSwipes = model.params.maxSwipes == null ? 0 : model.params.maxSwipes;

        By by = new NativeAndroidBySelector().pickFrom(strategy, selector);

        UiSelector uiselector;
        if (by instanceof By.ByAccessibilityId) {
            uiselector = new UiSelector().description(selector);
        } else if (by instanceof By.ByClass) {
            uiselector = new UiSelector().className(selector);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            uiselector = toSelector(by.getElementLocator());
        } else {
            throw new InvalidArgumentException(String.format(
                            "Unsupported strategy: '%s'. " +
                            "The only supported strategies are: '%s', '%s', and '%s'.",
                            strategy,
                            SELECTOR_ACCESSIBILITY_ID,
                            SELECTOR_CLASS,
                            SELECTOR_ANDROID_UIAUTOMATOR));
        }

        Device.waitForIdle();

        scrollToElement(uiselector, maxSwipes);

        Logger.info(String.format("Scrolled via strategy: '%s' and selector '%s'.", strategy, selector));

        return new AppiumResponse(getSessionId(request));
    }
}
