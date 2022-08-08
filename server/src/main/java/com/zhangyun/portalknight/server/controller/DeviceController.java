package com.zhangyun.portalknight.server.controller;


import com.zhangyun.portalknight.server.domain.req.DeviceInitReq;
import com.zhangyun.portalknight.server.domain.req.LoginReq;
import com.zhangyun.portalknight.server.domain.response.Response;
import com.zhangyun.portalknight.server.domain.service.impl.DeviceServiceImpl;
import com.zhangyun.portalknight.server.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
@RestController
@RequestMapping("/device")
@Api("设备接口")
@Slf4j
public class DeviceController {

    @Autowired
    private DeviceServiceImpl deviceService;

    @ApiOperation("deviceInit")
    @GetMapping("/deviceInit")
    public Response deviceInit(DeviceInitReq req){
        log.info("设备初始化: {}", req);
        return deviceService.deviceInit(req);
    }

}
