package com.zhangyun.portalknight.server.domain.service;

import com.zhangyun.portalknight.server.domain.req.UserReq;
import com.zhangyun.portalknight.server.domain.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangyun.portalknight.server.domain.response.Response;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
public interface IUserService extends IService<User> {

    public List<User> findAllUsers();

    public Response createUser(UserReq userReq);

    public Response deleteUser(UserReq userReq) throws IOException;

    public User findUserByEmail(String email);
}
