package com.zhangyun.portalknight.server.handler;

import com.zhangyun.portalknight.core.message.FileReceiveSuccessMessage;
import com.zhangyun.portalknight.core.message.FileUploadMessage;
import com.zhangyun.portalknight.server.config.UploadServerConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/1 00:00
 * @since: 1.0
 */
@Slf4j
@Component
public class FileUploadHandler
        extends SimpleChannelInboundHandler<FileUploadMessage> {

    @Autowired
    private UploadServerConfig uploadServerConfig;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileUploadMessage msg) throws Exception {
        log.info("receive msg: {}", msg);
        // 路径拼接
        String filePath = msg.getFilePath();
        Path path = Paths.get(uploadServerConfig.getPath());
        Path absolutePath = path.resolve(Paths.get(filePath));
        File file = new File(absolutePath.toString());
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

        // write file
        randomAccessFile.seek(msg.getStartPos());
        randomAccessFile.write(msg.getMessageBody());
        randomAccessFile.close();

        FileUploadMessage fileUploadMessage = new FileUploadMessage();
        int length = msg.getMessageBody().length;
        fileUploadMessage.setStartPos(msg.getStartPos() + length);
        fileUploadMessage.setFilePath(msg.getFilePath());
        ctx.writeAndFlush(fileUploadMessage);
        log.info("send msg: {}", fileUploadMessage);
    }
}
