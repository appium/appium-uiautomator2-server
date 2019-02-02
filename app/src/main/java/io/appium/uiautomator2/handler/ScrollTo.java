package io.appium.uiautomator2.handler;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.internal.NativeAndroidBySelector;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_ACCESSIBILITY_ID;
import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_ANDROID_UIAUTOMATOR;
import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_CLASS;
import static io.appium.uiautomator2.utils.Device.scrollToElement;

import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelector;

public class ScrollTo extends SafeRequestHandler {

    public ScrollTo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request)
        throws JSONException, UiObjectNotFoundException
    {
        String json = getPayload(request).toString();
        String strategy  = "$.params.strategy";
        String selector  = "$.params.selector";
        String maxSwipes = "$.params.maxSwipes";

        String strategyString = JsonPath.compile(strategy).read(json);
        String selectorString = JsonPath.compile(selector).read(json);

        int maxSearchSwipes;
        try {
            maxSearchSwipes = JsonPath.compile(maxSwipes).read(json);
        } catch (InvalidPathException e) {
            Logger.info("The parameter 'maxSwipes' is either not specified " +
                        "or mistyped. Using 0 as the default value.");
            maxSearchSwipes = 0;
        }

        By by = new NativeAndroidBySelector().pickFrom(strategyString, selectorString);

        UiSelector uiselector;
        if (by instanceof By.ByAccessibilityId) {
            uiselector = new UiSelector().description(selectorString);
        } else if (by instanceof By.ByClass) {
            uiselector = new UiSelector().className(selectorString);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            uiselector = toSelector(by.getElementLocator());
        } else {
            return new AppiumResponse(
                    getSessionId(request),
                    WDStatus.UNKNOWN_ERROR,
                    String.format(
                            "Unsupported strategy: '%s'. " +
                            "The only supported strategies are: '%s', '%s', and '%s'.",
                            strategyString,
                            SELECTOR_ACCESSIBILITY_ID,
                            SELECTOR_CLASS,
                            SELECTOR_ANDROID_UIAUTOMATOR));
        }

        Device.waitForIdle();

        scrollToElement(uiselector, maxSearchSwipes);

        Logger.info(String.format("Scrolled via strategy: '%s' and selector '%s'.",
                                  strategyString, selectorString));

        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }
}
