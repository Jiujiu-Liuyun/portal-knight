package com.zhangyun.portalknight.server.domain.req;

import lombok.Data;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/8 00:19
 * @since: 1.0
 */
@Data
public class LoginReq {

    private String username;

    private String password;

    private String email;

    private String deviceid;
}
