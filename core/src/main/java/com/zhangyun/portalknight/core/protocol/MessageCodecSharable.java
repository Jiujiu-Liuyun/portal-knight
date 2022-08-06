package com.zhangyun.portalknight.core.protocol;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import com.zhangyun.portalknight.core.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/30 02:05
 * @since: 1.0
 */
@ChannelHandler.Sharable
@Slf4j
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    @Value("${codec.version}")
    private int version;

    @Value("${codec.serialAlgorithm}")
    private int serialAlgorithm;

    /**
     * 消息编码：从MessageDTO对象转为byte[]对象
     * @param cxt
     * @param message
     * @param list
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext cxt, Message message, List<Object> list) throws Exception {
        log.info("message encode: {}", message);
        ByteBuf out = cxt.alloc().buffer();
        // magic --- 12bytes
        out.writeBytes(new byte[]{0x70, 0x6f, 0x72, 0x74, 0x61, 0x6c,
                0x6b, 0x6e, 0x69, 0x67, 0x68, 0x74});
        // version --- byte
        out.writeByte(version);
        // 序列化算法 --- byte
        out.writeByte(serialAlgorithm);
        // 指令类型 --- int
        out.writeInt(message.getMessageType());
        // 对齐填充 --- 18bytes + 14bytes = 32bytes
        byte[] alignBytes = new byte[14];
        Arrays.fill(alignBytes, (byte) 0xff);
        out.writeBytes(alignBytes);

        // 消息体
        byte[] messageBody = message.getMessageBody();
        // 消息对象 --> byte数组
        message.setMessageBody(null);
        byte[] messageHeader = JSONObject.toJSONString(message).getBytes();

        // 消息体: messageHeaderLength(int) + messageHeader + messageBodyLength(int) + messageBody
        if (ObjectUtil.isEmpty(messageBody)) {
            // 长度
            out.writeInt(messageHeader.length + 2 * 4);
            out.writeInt(messageHeader.length);
            out.writeBytes(messageHeader);
            out.writeInt(0);
        } else {
            out.writeInt(messageHeader.length + messageBody.length + 2 * 4);
            out.writeInt(messageHeader.length);
            out.writeBytes(messageHeader);
            out.writeInt(messageBody.length);
            out.writeBytes(messageBody);
        }

        list.add(out);
    }

    /**
     * 消息解码
     * @param cxt
     * @param in
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext cxt, ByteBuf in, List<Object> list) throws Exception {
        // magic
        ByteBuf magic = in.readBytes(12);
        // version
        byte version = in.readByte();
        // 序列化算法
        byte serialAlgorithm = in.readByte();
        // 指令类型
        int messageType = in.readInt();
        Class<? extends Message> messageClass = Message.getMessageClass(messageType);
        // 对齐填充
        in.readBytes(14);

        // 消息长度
        int length = in.readInt();
        // 消息
        byte[] bytes = new byte[length];

        // 消息头
        int messageHeaderLength = in.readInt();
        byte[] messageHeader = new byte[messageHeaderLength];
        in.readBytes(messageHeader, 0, messageHeaderLength);
        log.info("messageHeader: {}, messageClass: {}", new String(messageHeader), messageClass);
        Message message = JSONObject.parseObject(new String(messageHeader), messageClass);
        // 消息体
        int messageBodyLength = in.readInt();
        byte[] messageBody = null;
        if (messageBodyLength != 0) {
            messageBody = new byte[messageBodyLength];
            in.readBytes(messageBody, 0, messageBodyLength);
        }

        message.setMessageBody(messageBody);
        log.info("message decode: {}", message);
        list.add(message);
    }
}
