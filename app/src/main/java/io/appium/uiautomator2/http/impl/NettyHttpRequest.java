package io.appium.uiautomator2.http.impl;

import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.http.IHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;

public class NettyHttpRequest implements IHttpRequest {
    private final FullHttpRequest request;
    private final Map<String, Object> data;

    public NettyHttpRequest(FullHttpRequest request) {
        this.request = request;
        this.data = new HashMap<String, Object>();
    }

    @Override
    public String method() {
        return request.getMethod().name();
    }

    @Override
    public String uri() {
        return request.getUri();
    }

    @Override
    public String body() {
        return request.content().toString(CharsetUtil.UTF_8);
    }

    @Override
    public String header(String name) {
        return request.headers().get(name);
    }

    @Override
    public Map<String, Object> data() {
        return data;
    }
}
