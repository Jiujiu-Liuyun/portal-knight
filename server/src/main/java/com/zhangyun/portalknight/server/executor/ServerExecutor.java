package com.zhangyun.portalknight.server.executor;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/2 00:42
 * @since: 1.0
 */
@Component
public class ServerExecutor extends ThreadPoolExecutor {

    public ServerExecutor() {
        super(4, 8, 60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new AbortPolicy());
    }

    public ServerExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
}
