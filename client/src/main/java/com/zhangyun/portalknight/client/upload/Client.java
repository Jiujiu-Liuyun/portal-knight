package com.zhangyun.portalknight.client.upload;

import com.zhangyun.portalknight.client.config.UploadClientConfig;
import com.zhangyun.portalknight.client.excutor.ClientExecutor;
import com.zhangyun.portalknight.client.handler.FileUploadHandler;
import com.zhangyun.portalknight.core.message.FileUploadMessage;
import com.zhangyun.portalknight.core.message.TestMessage;
import com.zhangyun.portalknight.core.protocol.FrameDecoder;
import com.zhangyun.portalknight.core.protocol.MessageCodecSharable;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/31 16:09
 * @since: 1.0
 */
@Slf4j
@Component
public class Client implements ApplicationRunner {

    @Autowired
    private ClientExecutor clientExecutor;

    @Autowired
    private UploadClientConfig uploadClientConfig;

    @Autowired
    private FileUploadHandler fileUploadHandler;

    @Value("${uploadserver.host}")
    private String host;

    @Value("${uploadserver.port}")
    private int port;

    private Bootstrap bootstrap;
    private NioEventLoopGroup group = new NioEventLoopGroup();
    private LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
    private MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();

    @Override
    public void run(ApplicationArguments args) {
        clientExecutor.execute(() -> {
            try {
                log.debug("客户端启动");
                bootstrap = new Bootstrap()
                        .channel(NioSocketChannel.class)
                        .group(group)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new FrameDecoder());
                                ch.pipeline().addLast(LOGGING_HANDLER);
                                ch.pipeline().addLast(MESSAGE_CODEC);
                                ch.pipeline().addLast(fileUploadHandler);
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                        String file = "txt";
//                                        Path filePath = Paths.get(file);
//                                        Path absolutePath = Paths.get(uploadClientConfig.getPath()).resolve(filePath);
//                                        byte[] messageBody = new byte[10];
//                                        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(absolutePath.toString()), "rw");
//                                        randomAccessFile.seek(0);
//                                        randomAccessFile.read(messageBody);
//
//                                        FileUploadMessage fileUploadMessage = new FileUploadMessage();
//                                        fileUploadMessage.setFilePath(file);
//                                        fileUploadMessage.setStartPos(0L);
//                                        fileUploadMessage.setMessageBody(messageBody);
//                                        ctx.writeAndFlush(fileUploadMessage);

                                        for (int i = 0; i < 100; i++) {
                                            TestMessage message = new TestMessage();
                                            message.setId(i);
                                            ctx.writeAndFlush(message);
                                        }


                                    }

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    }
                                });
                            }
                        });
                Channel channel = bootstrap.connect(host, port).sync().channel();
                log.debug("启动成功");
                channel.closeFuture().sync();
            } catch (Exception e) {
                log.error("start error: " ,e);
            } finally {
                group.shutdownGracefully();
            }
        });
    }
}
