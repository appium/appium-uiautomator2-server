package io.appium.uiautomator2.handler;

import org.apache.commons.lang.NotImplementedException;
import org.json.JSONException;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.NetworkConnectionEnum;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;


public class NetworkConnection extends SafeRequestHandler {

    public NetworkConnection(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        try {
            int requestedType = getPayload(request).getInt("type");
            NetworkConnectionEnum networkType = NetworkConnectionEnum.getNetwork(requestedType);
            switch (networkType) {
                case WIFI:
                    return WifiHandler.toggle(true, getSessionId(request));
                case DATA:
                case AIRPLANE:
                case ALL:
                case NONE:
                    return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, new NotImplementedException("Setting Network Connection to: " + networkType.getNetworkType() + " :is not implemented"));
                default:
                    return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, new UiAutomator2Exception("Invalid Network Connection type: " + requestedType));
            }
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        }
    }
}
