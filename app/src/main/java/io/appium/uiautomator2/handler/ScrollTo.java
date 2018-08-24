package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.UiObjectNotFoundException;

import com.jayway.jsonpath.JsonPath;

import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.scrollTo;

public class ScrollTo extends SafeRequestHandler {

    public ScrollTo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException,
            UiObjectNotFoundException {
        String json = getPayload(request).toString();
        String selector = "$.params.selector", uiSelectorString, scrollToString = "";
        uiSelectorString = JsonPath.compile(selector).read(json);
        Device.waitForIdle();
        // Extracting (\"Radio Group\") text from the String
        // TODO This logic needs to be changed according to the request body from the Driver
        Matcher m = Pattern.compile("\\(\"([^)]+)\"\\)").matcher(uiSelectorString);
        while (m.find()) {
            scrollToString = m.group(1);
        }
        scrollTo(scrollToString);
        Logger.info("Scrolled to String : ", scrollToString);
        return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, true);
    }
}
