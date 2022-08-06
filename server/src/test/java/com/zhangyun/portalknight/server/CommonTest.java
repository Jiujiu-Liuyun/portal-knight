package com.zhangyun.portalknight.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/7/31 11:30
 * @since: 1.0
 */
@Slf4j
public class CommonTest {

    @Test
    public void test() {
        log.info("{}", Long.MAX_VALUE / 1024.0 / 1024.0 / 1024.0);

        Path source = Paths.get("/Users/zhangyun/test/source");
        Path target = Paths.get("/Users/zhangyun/test/target");

        Path path = Paths.get("/Users/zhangyun/test/source/txt");

        String p = source.toString();
        File file = new File(p.toString());
        log.info("{}", file.getAbsoluteFile());
        log.info("{}", file.getName());
        log.info("{}", file.lastModified());
        log.info("{}", source.relativize(path));
        log.info("{}", target.resolve(source.relativize(path)));
    }


}
