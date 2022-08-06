package com.zhangyun.portalknight.server.handler;

import com.zhangyun.portalknight.core.message.TestMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @program: portalknight
 * @author: zhangyun
 * @date: 2022-08-02 10:43
 **/
@Slf4j
@Component
public class TestHandler extends SimpleChannelInboundHandler<TestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TestMessage msg) throws Exception {
        log.info("receive msg: {}", msg);
    }
}
