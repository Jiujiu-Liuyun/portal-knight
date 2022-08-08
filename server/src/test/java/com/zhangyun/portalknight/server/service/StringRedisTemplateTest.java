package com.zhangyun.portalknight.server.service;

import com.zhangyun.portalknight.server.ServerApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 12:01
 * @since: 1.0
 */
@Component
@Slf4j
public class StringRedisTemplateTest extends ServerApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void incrTest() {
        long start = System.currentTimeMillis();
        Long lock = redisTemplate.opsForValue().increment("lock");
        log.info("{} s", (System.currentTimeMillis() - start) / 1000.0);
        log.info("lock: {}", lock);
    }
}
