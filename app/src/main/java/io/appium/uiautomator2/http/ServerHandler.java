package io.appium.uiautomator2.http;

import java.util.List;

import io.appium.uiautomator2.http.impl.NettyHttpRequest;
import io.appium.uiautomator2.http.impl.NettyHttpResponse;
import io.appium.uiautomator2.utils.Logger;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;

import static io.netty.handler.codec.http.HttpHeaders.Names.CACHE_CONTROL;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.PRAGMA;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private final List<IHttpServlet> httpHandlers;

    public ServerHandler(List<IHttpServlet> handlers) {
        this.httpHandlers = handlers;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Logger.info("channel read invoked!");
        if (!(msg instanceof FullHttpRequest)) {
            return;
        }

        FullHttpRequest request = (FullHttpRequest) msg;
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        boolean keepAlive = HttpHeaders.isKeepAlive(request);
        response.headers().set(CONNECTION, keepAlive
                ? HttpHeaders.Values.KEEP_ALIVE
                : HttpHeaders.Values.CLOSE);
        response.headers().set(PRAGMA, "no-cache");
        response.headers().set(CACHE_CONTROL, "no-store");

        Logger.info("channel read: " + request.getMethod().toString() + " " + request.getUri());

        IHttpRequest httpRequest = new NettyHttpRequest(request);
        IHttpResponse httpResponse = new NettyHttpResponse(response);
        for (IHttpServlet handler : httpHandlers) {
            handler.handleHttpRequest(httpRequest, httpResponse);
            if (httpResponse.isClosed()) {
                break;
            }
        }
        if (!httpResponse.isClosed()) {
            httpResponse.setStatus(404);
            httpResponse.end();
        }

        ChannelFuture future = ctx.write(response);
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
        ctx.fireChannelReadComplete();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Logger.error("Error handling request", cause);
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
