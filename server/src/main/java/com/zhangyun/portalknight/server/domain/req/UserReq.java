package com.zhangyun.portalknight.server.domain.req;

import lombok.Data;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 17:24
 * @since: 1.0
 */
@Data
public class UserReq {

    private String username;

    private String password;

    private String email;

}
