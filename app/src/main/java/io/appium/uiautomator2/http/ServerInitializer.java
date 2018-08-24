package io.appium.uiautomator2.http;

import java.util.List;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private final List<io.appium.uiautomator2.http.IHttpServlet> handlers;

    public ServerInitializer(List<io.appium.uiautomator2.http.IHttpServlet> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("codec", new HttpServerCodec());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("io/appium/uiautomator2/handler", new io.appium.uiautomator2.http.ServerHandler(handlers));
    }
}
