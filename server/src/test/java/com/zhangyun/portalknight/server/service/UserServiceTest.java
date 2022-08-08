package com.zhangyun.portalknight.server.service;

import com.zhangyun.portalknight.server.ServerApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 18:57
 * @since: 1.0
 */
@Component
@Slf4j
public class UserServiceTest extends ServerApplicationTests {

    @Value("${uploadserver.path}")
    private String path;

    @Test
    public void pathTest() {
        log.info("{}", path);
        String absolutePath = path + "/";
        File dir = new File(absolutePath);
        log.info("{}", dir);
    }
}
