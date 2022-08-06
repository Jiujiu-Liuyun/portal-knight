package com.zhangyun.portalknight.client.monitor;

import cn.hutool.core.util.ObjectUtil;
import com.zhangyun.portalknight.client.excutor.ClientExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * description: 定义文件监视器，可用来实现热更配置文件/监听文件场景
 *
 * @author: zhangyun
 * @date: 2022/7/22 00:55
 * @since: 1.0
 */
@Service
@Slf4j
public class FBFileMonitor implements ApplicationRunner {

    @Autowired
    private FileAlterationListenerAdaptor listener;    // 事件处理类对象

    @Value("${uploadclient.path}")
    private String path;

    @Value("${uploadclient.interval}")
    private Integer interval;

    @Autowired
    private ClientExecutor clientExecutor;

    /***
     * 开启监听
     */
    public void start() throws Exception {
        if (ObjectUtil.isEmpty(path)) {
            throw new IllegalArgumentException("Listen path must not be blank");
        }
        if (ObjectUtil.isEmpty(listener)) {
            throw new IllegalArgumentException("Listener must not be null");
        }

        // 设定观察者，监听文件
        FileAlterationObserver observer = new FileAlterationObserver(path);

        // 给观察者添加监听事件
        observer.addListener(listener);

        // 开启一个监视器，监听频率是interval
        // FileAlterationMonitor本身实现了 Runnable，是单独的一个线程，按照设定的时间间隔运行
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);

        monitor.addObserver(observer);

        try {
            monitor.start();
            log.info("File Monitor启动成功！");
        } catch (Exception e) {
            log.error("File Monitor启动失败！{}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 服务启动时自动启动文件监听器
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) {
        clientExecutor.execute(() -> {
            try {
                start();
            } catch (Exception e) {
                log.error("启动文件监听器异常: {}", e.getMessage(), e);
            }
        });
    }
}
