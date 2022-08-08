package com.zhangyun.portalknight.server.domain.req;

import lombok.Data;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/8 23:51
 * @since: 1.0
 */
@Data
public class DeviceInitReq {

    private String username;

    private String password;

    private String email;

    private String deviceDesc;

}
