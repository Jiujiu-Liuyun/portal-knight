package com.zhangyun.portalknight.server.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 17:16
 * @since: 1.0
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        this.setFieldValByName("createTime", timestamp, metaObject);
        this.setFieldValByName("updateTime", timestamp, metaObject);
        log.info("insert {}, current: {}", metaObject, timestamp);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        this.setFieldValByName("updateTime", timestamp, metaObject);
        log.info("update {}, current: {}", metaObject, timestamp);
    }
}
