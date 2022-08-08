package com.zhangyun.portalknight.server.domain.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangyun.portalknight.server.domain.convert.UserConvert;
import com.zhangyun.portalknight.server.domain.req.UserReq;
import com.zhangyun.portalknight.server.domain.model.User;
import com.zhangyun.portalknight.server.domain.mapper.UserMapper;
import com.zhangyun.portalknight.server.domain.response.Response;
import com.zhangyun.portalknight.server.domain.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Value("${uploadserver.path}")
    private String prefixPath;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConvert userConvert;

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public Response createUser(UserReq userReq) {
        // convert
        User user = userConvert.userDTOToUser(userReq);
        // 密码加密
        String pwMd5 = DigestUtil.md5Hex(userReq.getPassword());
        user.setPassword(pwMd5);
        user.setRootpath(userReq.getEmail());
        int insert = userMapper.insert(user);
        if (insert <= 0) {
            log.error("创建用户失败, user: {}", user);
            throw new RuntimeException("创建用户失败");
        }
        String absolutePath = prefixPath + "/" + userReq.getEmail();
        File dir = new File(absolutePath);
        if (dir.exists()) {
            log.error("文件夹已存在, dir: {}", dir);
            throw new RuntimeException("文件夹已存在");
        }
        if (dir.mkdir()) {
            log.info("创建文件夹成功: {}", dir);
            return Response.ok();
        } else {
            log.warn("创建文件夹失败: {}", dir);
            return Response.fail("创建文件夹失败");
        }
    }

    @Override
    public Response deleteUser(UserReq userReq) throws IOException {
        // convert
        String absolutePath = prefixPath + "/" + userReq.getEmail();
        File dir = new File(absolutePath);
        FileUtils.delete(dir);
        User user = userConvert.userDTOToUser(userReq);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", userReq.getEmail());
        int result = userMapper.delete(wrapper);
        if (result <= 0) {
            log.error("删除用户失败: {}", userReq);
            throw new RuntimeException("删除用户失败");
        }
        return Response.ok();
    }

    @Override
    public User findUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        return userMapper.selectOne(wrapper);
    }

}
