package com.zhangyun.portalknight.server.domain.service.impl;

import com.zhangyun.portalknight.server.domain.model.File;
import com.zhangyun.portalknight.server.domain.mapper.FileMapper;
import com.zhangyun.portalknight.server.domain.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
