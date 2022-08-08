package com.zhangyun.portalknight.server.service;

import com.zhangyun.portalknight.server.ServerApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 11:32
 * @since: 1.0
 */
@Component
@Slf4j
public class CacheServiceTest extends ServerApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    public void addAndRead() {
        String name = cacheService.get("name");
        log.info("get key: name, value: {}", name);
    }
}
