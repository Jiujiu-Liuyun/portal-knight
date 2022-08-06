package com.zhangyun.portalknight.server.upload;

import com.zhangyun.portalknight.core.protocol.FrameDecoder;
import com.zhangyun.portalknight.core.protocol.MessageCodecSharable;
import com.zhangyun.portalknight.server.executor.ServerExecutor;
import com.zhangyun.portalknight.server.handler.FileUploadHandler;
import com.zhangyun.portalknight.server.handler.TestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/31 15:20
 * @since: 1.0
 */
@Component
@Slf4j
public class Server implements ApplicationRunner {

    @Autowired
    private ServerExecutor serverExecutor;

    @Autowired
    private TestHandler testHandler;
    @Autowired
    private FileUploadHandler fileUploadHandler;

    @Value("${uploadserver.host}")
    private String host;

    @Value("${uploadserver.port}")
    private int port;

    private NioEventLoopGroup boss = new NioEventLoopGroup();
    private NioEventLoopGroup worker = new NioEventLoopGroup();
    private LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
    private MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
    private ServerBootstrap serverBootstrap = new ServerBootstrap();
    private Channel channel;

    @Override
    public void run(ApplicationArguments args) {
        serverExecutor.execute(() -> {
            try {
                log.info("服务端启动。。。");
                serverBootstrap.channel(NioServerSocketChannel.class)
                        .group(boss, worker)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new FrameDecoder());
                                ch.pipeline().addLast(LOGGING_HANDLER);
                                ch.pipeline().addLast(MESSAGE_CODEC);
                                ch.pipeline().addLast(fileUploadHandler);
                                ch.pipeline().addLast(testHandler);
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        log.info("msg: {}", msg);
                                    }

                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    }
                                });
                            }
                        });
                channel = serverBootstrap.bind(port).sync().channel();
                channel.closeFuture().sync();
                log.info("server close");
            } catch (Exception e) {
                log.error("server error: ", e);
            } finally {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        });
    }
}
