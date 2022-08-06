package com.zhangyun.portalknight.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/30 01:41
 * @since: 1.0
 */
@RestController
@Slf4j
@Api("测试Swagger")
public class ControllerTest {

    @ApiOperation("test")
    @GetMapping("/test")
    public String test(){
        log.info("test controller");
        return "test";
    }
}
