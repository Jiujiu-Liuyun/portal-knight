package com.zhangyun.portalknight.server.domain.mapper;

import com.zhangyun.portalknight.server.domain.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
public interface UserMapper extends BaseMapper<User> {

    // test
    public List<User> findAllUsers();

}
