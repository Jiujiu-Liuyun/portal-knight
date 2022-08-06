package com.zhangyun.portalknight.client.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @program: portalknight
 * @author: zhangyun
 * @date: 2022-08-01 11:23
 **/
@Data
@Component
public class UploadClientConfig {

    @Value("${uploadclient.path}")
    private String path;

    @Value("${uploadclient.maxsize}")
    private int maxsize;

}
