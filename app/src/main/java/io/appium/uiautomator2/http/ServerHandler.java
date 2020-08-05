/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.http;

import java.util.List;

import io.appium.uiautomator2.common.exceptions.UnknownCommandException;
import io.appium.uiautomator2.http.impl.NettyHttpRequest;
import io.appium.uiautomator2.http.impl.NettyHttpResponse;
import io.appium.uiautomator2.server.AppiumServlet;
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

        Logger.info(String.format("channel read: %s %s", request.getMethod().toString(), request.getUri()));

        IHttpRequest httpRequest = new NettyHttpRequest(request);
        IHttpResponse httpResponse = new NettyHttpResponse(response);
        for (IHttpServlet handler : httpHandlers) {
            handler.handleHttpRequest(httpRequest, httpResponse);
            if (httpResponse.isClosed()) {
                break;
            }
        }
        if (!httpResponse.isClosed()) {
            Object sessionId = httpRequest.data().get(AppiumServlet.SESSION_ID_KEY);
            new AppiumResponse(sessionId == null ? null : (String) sessionId, new UnknownCommandException())
                    .renderTo(httpResponse);
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
        Logger.error("exception caught", cause);
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
