package io.appium.uiautomator2.http.impl;

import java.nio.charset.Charset;

import io.appium.uiautomator2.http.IHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

public class NettyHttpResponse implements IHttpResponse {

    private final FullHttpResponse response;
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String LOCATION = "location";
    private boolean closed;
    private Charset charset = CharsetUtil.UTF_8;


    public NettyHttpResponse(FullHttpResponse response) {
        this.response = response;
        response.headers().add(CONTENT_ENCODING, "identity");
    }

    public IHttpResponse setStatus(int status) {
        response.setStatus(HttpResponseStatus.valueOf(status));
        return this;
    }

    public IHttpResponse setContentType(String mimeType) {
        response.headers().add(CONTENT_TYPE, mimeType);
        return this;
    }

    public IHttpResponse setContent(byte[] data) {
        response.headers().add(CONTENT_LENGTH, data.length);
        response.content().writeBytes(data);
        return this;
    }

    public IHttpResponse setContent(String message) {
        setContent(message.getBytes(charset));
        return this;
    }

    public IHttpResponse sendRedirect(String to) {
        setStatus(HttpResponseStatus.MOVED_PERMANENTLY.code());
        response.headers().add(LOCATION, to);
        return this;
    }

    public IHttpResponse sendTemporaryRedirect(String to) {
        setStatus(HttpResponseStatus.FOUND.code());
        response.headers().add(LOCATION, to);
        return this;
    }

    @Override
    public void end() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    @Override
    public IHttpResponse setEncoding(Charset charset) {
        this.charset = charset;
        return this;
    }
}
