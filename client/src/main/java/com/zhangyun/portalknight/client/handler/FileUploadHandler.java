package com.zhangyun.portalknight.client.handler;

import com.zhangyun.portalknight.client.config.UploadClientConfig;
import com.zhangyun.portalknight.core.message.FileUploadMessage;
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
 * @program: portalknight
 * @author: zhangyun
 * @date: 2022-08-01 11:06
 **/
@Slf4j
@Component
public class FileUploadHandler extends SimpleChannelInboundHandler<FileUploadMessage> {

    @Autowired
    private UploadClientConfig uploadClientConfig;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileUploadMessage msg)
            throws Exception {
        // 路径拼接
        log.info("receive msg: {}", msg);
        String filePath = msg.getFilePath();
        Path path = Paths.get(uploadClientConfig.getPath());
        Path absolutePath = path.resolve(Paths.get(filePath));

        // read file
        File file = new File(absolutePath.toString());
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        // read length
        int readLength = (int) Math.min(randomAccessFile.length() - msg.getStartPos(), uploadClientConfig.getMaxsize());
        if (readLength <= 0) {
            log.info("文件 {} 上传完毕", filePath);
            return;
        }
        randomAccessFile.seek(msg.getStartPos());
        byte[] messageBody = new byte[readLength];
        randomAccessFile.read(messageBody);
        randomAccessFile.close();

        // sen msg
        FileUploadMessage fileUploadMessage = new FileUploadMessage();
        fileUploadMessage.setFilePath(filePath);
        fileUploadMessage.setStartPos(msg.getStartPos());
        fileUploadMessage.setMessageBody(messageBody);
        ctx.writeAndFlush(fileUploadMessage);
        log.info("send msg: {}", fileUploadMessage);
    }
}
