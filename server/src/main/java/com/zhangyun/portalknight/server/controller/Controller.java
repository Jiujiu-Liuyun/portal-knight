package com.zhangyun.portalknight.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/30 01:41
 * @since: 1.0
 */
@RequestMapping("/")
@RestController
@Slf4j
public class Controller {

    @RequestMapping("/test")
    public String test(){
        log.info("test controller");
        return "test";
    }
}
