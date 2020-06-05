package io.appium.uiautomator2.http;

import io.appium.uiautomator2.utils.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class KeepAliveStateHandler extends IdleStateHandler {
    private static final int MAX_IDLE_TIME_SEC = 60;

    public KeepAliveStateHandler() {
        super(MAX_IDLE_TIME_SEC, 0, 0);
    }

    @Override
    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) {
        if (e.state() == IdleState.READER_IDLE) {
            Logger.info(String.format("%s: closing the channel", e.state().name()));
            ctx.close();
        }
    }
}
