package com.zhangyun.portalknight.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/30 01:49
 * @since: 1.0
 */
@Component
@Data
public class UploadServerConfig {

    @Value("${uploadserver.path}")
    private String path;

}
