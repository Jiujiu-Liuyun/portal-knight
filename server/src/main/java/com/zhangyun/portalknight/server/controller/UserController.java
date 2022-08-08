package com.zhangyun.portalknight.server.controller;


import com.zhangyun.portalknight.server.domain.req.UserReq;
import com.zhangyun.portalknight.server.domain.model.User;
import com.zhangyun.portalknight.server.domain.response.Response;
import com.zhangyun.portalknight.server.domain.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
@RestController
@RequestMapping("/user")
@Api("用户接口")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("findAllUser")
    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        List<User> users = userService.findAllUsers();
        log.info("find all users: {}", users);
        return users;
    }

    @ApiOperation("createUser")
    @GetMapping("/createUser")
    public Response createUser(UserReq userReq){
        return userService.createUser(userReq);
    }

    @ApiOperation("deleteUser")
    @GetMapping("/deleteUser")
    public Response deleteUser(UserReq userReq) throws IOException {
        return userService.deleteUser(userReq);
    }


}
