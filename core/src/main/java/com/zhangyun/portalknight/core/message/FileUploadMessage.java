package com.zhangyun.portalknight.core.message;

import lombok.Data;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/31 11:11
 * @since: 1.0
 */
@Data
public class FileUploadMessage extends Message {

    private String filePath;

    private Long startPos;

    @Override
    public int getMessageType() {
        return FILE_UPLOAD_CMD;
    }
}
