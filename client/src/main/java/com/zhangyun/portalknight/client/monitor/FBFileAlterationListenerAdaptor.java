package com.zhangyun.portalknight.client.monitor;

import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * description: 文件变动事件处理器类
 *
 * @author: zhangyun
 * @date: 2022/7/22 00:51
 * @since: 1.0
 */
@Service
@Slf4j
public class FBFileAlterationListenerAdaptor extends FileAlterationListenerAdaptor {
    @Override
    public void onStart(FileAlterationObserver observer) {
        log.info("start...");
        super.onStart(observer);
    }

    @Override
    public void onDirectoryCreate(File directory) {
        log.info("directory create: {}, lastModified: {}", directory, new DateTime(directory.lastModified()));
        super.onDirectoryCreate(directory);
    }

    @Override
    public void onDirectoryChange(File directory) {
        log.info("directory change: {}, lastModified: {}", directory, new DateTime(directory.lastModified()));
        super.onDirectoryChange(directory);
    }

    @Override
    public void onDirectoryDelete(File directory) {
        log.info("directory delete: {}, lastModified: {}", directory, new DateTime(directory.lastModified()));
        super.onDirectoryDelete(directory);
    }

    @Override
    public void onFileCreate(File file) {
        log.info("file create: {}, lastModified: {}", file, new DateTime(file.lastModified()));
        super.onFileCreate(file);
    }

    @Override
    public void onFileChange(File file) {
        log.info("file change: {}, lastModified: {}", file, new DateTime(file.lastModified()));
        super.onFileChange(file);
    }

    @Override
    public void onFileDelete(File file) {
        log.info("file delete: {}, lastModified: {}", file, new DateTime(file.lastModified()));
        super.onFileDelete(file);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        log.info("stop...");
        super.onStop(observer);
    }
}
