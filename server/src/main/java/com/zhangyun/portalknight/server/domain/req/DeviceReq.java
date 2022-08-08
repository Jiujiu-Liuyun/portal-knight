package com.zhangyun.portalknight.server.domain.req;

import lombok.Data;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 22:39
 * @since: 1.0
 */
@Data
public class DeviceReq {

    private String deviceid;

    private String deviceDesc;

    private Boolean isOnline;

    private String token;
}
