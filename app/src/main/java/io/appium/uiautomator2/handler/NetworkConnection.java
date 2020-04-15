

package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.model.api.NetworkConnectionModel;
import io.appium.uiautomator2.utils.WifiHandler;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.NetworkConnectionEnum;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class NetworkConnection extends SafeRequestHandler {

    public NetworkConnection(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        int requestedType = toModel(request, NetworkConnectionModel.class).type;
        NetworkConnectionEnum networkType = NetworkConnectionEnum.getNetwork(requestedType);
        switch (networkType) {
            case WIFI:
                return WifiHandler.toggle(true, getSessionId(request));
            case DATA:
            case AIRPLANE:
            case ALL:
            case NONE:
                throw new RuntimeException(String.format("Setting Network Connection to '%s' is not implemented",
                        networkType.getNetworkType()));
            default:
                throw new InvalidArgumentException("Invalid Network Connection type: " + requestedType);
        }
    }
}
