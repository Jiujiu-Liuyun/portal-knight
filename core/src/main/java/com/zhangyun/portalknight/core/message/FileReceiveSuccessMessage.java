package com.zhangyun.portalknight.core.message;

import lombok.Data;

/**
 * description:
 *
 * @program: portalknight
 * @author: zhangyun
 * @date: 2022-08-01 11:00
 **/
@Data
public class FileReceiveSuccessMessage extends Message{

    private String filePath;

    private int nextPos;

    @Override
    public int getMessageType() {
        return FILE_RECEIVE_SUCCESS_CMD;
    }
}
