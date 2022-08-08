package com.zhangyun.portalknight.server.controller;

import com.zhangyun.portalknight.server.domain.model.User;
import com.zhangyun.portalknight.server.domain.req.LoginReq;
import com.zhangyun.portalknight.server.domain.response.Response;
import com.zhangyun.portalknight.server.domain.service.IUserService;
import com.zhangyun.portalknight.server.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/8 23:43
 * @since: 1.0
 */
@RestController
@RequestMapping("/login")
@Api("登录接口")
@Slf4j
public class LoginController {

    @Autowired
    private AuthService authService;

    @ApiOperation("login")
    @GetMapping("/login")
    public Response login(LoginReq req){
        log.info("设备登录: {}", req);
        return authService.login(req);
    }

}
