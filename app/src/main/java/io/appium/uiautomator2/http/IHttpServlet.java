package io.appium.uiautomator2.http;

public interface IHttpServlet {
    void handleHttpRequest(io.appium.uiautomator2.http.IHttpRequest IHttpRequest, io.appium.uiautomator2.http.IHttpResponse httpResponse) throws Exception;
}
