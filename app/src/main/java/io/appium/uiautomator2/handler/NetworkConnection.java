package io.appium.uiautomator2.handler;

import org.apache.commons.lang.NotImplementedException;
import org.json.JSONException;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.NetworkConnectionEnum;


public class NetworkConnection extends SafeRequestHandler {

    public NetworkConnection(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        int requestedType = toJSON(request).getInt("type");
        NetworkConnectionEnum networkType = NetworkConnectionEnum.getNetwork(requestedType);
        switch (networkType) {
            case WIFI:
                return WifiHandler.toggle(true, getSessionId(request));
            case DATA:
            case AIRPLANE:
            case ALL:
            case NONE:
                throw new NotImplementedException(String.format("Setting Network Connection to '%s' is not implemented",
                        networkType.getNetworkType()));
            default:
                throw new InvalidArgumentException("Invalid Network Connection type: " + requestedType);
        }
    }
}
