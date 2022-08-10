package com.zhangyun.portalknight.server.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zhangyun.portalknight.server.domain.convert.DeviceConvert;
import com.zhangyun.portalknight.server.domain.model.Device;
import com.zhangyun.portalknight.server.domain.req.DeviceReq;
import com.zhangyun.portalknight.server.domain.req.LoginReq;
import com.zhangyun.portalknight.server.domain.req.LogoutReq;
import com.zhangyun.portalknight.server.domain.model.User;
import com.zhangyun.portalknight.server.domain.response.Response;
import com.zhangyun.portalknight.server.domain.service.impl.DeviceServiceImpl;
import com.zhangyun.portalknight.server.domain.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 23:54
 * @since: 1.0
 */
@Service
@Slf4j
public class AuthService {

    @Value("${redis.user.session.key}")
    private String REDIS_SESSION_KEY;

    @Value("${sso.expiretime}")
    private long EXPIRE_TIME;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceConvert deviceConvert;

    public Response login(LoginReq req) {
        log.info("login: {}", req);
        //  通过ID获取设备信息
        Device device = (Device) deviceService.getDeviceById(req.getDeviceId())
                .getData().get("device");
        // 判断设备是否在线
        if (ObjectUtil.equal(device.getIsOnline(), true)) {
            return Response.fail("登录失败，设备已在线");
        }
        // 校验账号密码
        String pw = DigestUtil.md5Hex(req.getPassword());
        User user = userService.findUserByEmail(req.getEmail());
        if (ObjectUtil.equal(user.getUsername(), req.getUsername())
                && ObjectUtil.equal(user.getPassword(), pw)) {
            // 生成token
            UUID token = UUID.randomUUID();
            user.setPassword(null);
            // 将token插入缓存
            cacheService.set(REDIS_SESSION_KEY + ":" + token, user,
                    EXPIRE_TIME, TimeUnit.DAYS);
            // device库更新
            DeviceReq deviceReq = new DeviceReq();
            deviceReq.setDeviceId(req.getDeviceId());
            deviceReq.setIsOnline(true);
            deviceReq.setToken(token.toString());
            deviceService.updataStatus(deviceReq);
            return Response.ok(200, "登录成功", "token", token);
        } else {
            log.info("登录失败: {}", req);
            return Response.fail("登录失败");
        }
    }

    public Response logout(LogoutReq req) {
        log.info("logout: {}", req);
        //  通过ID获取设备信息
        Device device = (Device) deviceService.getDeviceById(req.getDeviceId())
                .getData().get("device");
        // 判断设备是否在线
        if (ObjectUtil.equal(device.getIsOnline(), false)) {
            return Response.fail("设备已离线");
        }
        // 删除token
        String key = REDIS_SESSION_KEY + ":" + device.getToken();
        log.info("删除缓存token, key: {}", key);
        cacheService.delete(key);
        // 更新mysql数据库
//        log.info("更新mysql数据库: deviceId: {}", device.getDeviceId());
        DeviceReq deviceReq = deviceConvert.deviceToDeviceReq(device);
        deviceReq.setIsOnline(false);
        deviceService.updataStatus(deviceReq);
        return Response.ok("登出成功");
    }

}
