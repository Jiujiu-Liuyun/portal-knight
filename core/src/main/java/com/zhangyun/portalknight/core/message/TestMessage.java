package com.zhangyun.portalknight.core.message;

import lombok.Data;

/**
 * description:
 *
 * @program: portalknight
 * @author: zhangyun
 * @date: 2022-08-02 10:39
 **/
@Data
public class TestMessage extends Message{

    private Integer id;

    @Override
    public int getMessageType() {
        return TEST_MESSAGE_CMD;
    }
}
