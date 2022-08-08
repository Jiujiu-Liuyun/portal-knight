package com.zhangyun.portalknight.server.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangyun.portalknight.server.domain.convert.DeviceConvert;
import com.zhangyun.portalknight.server.domain.model.Device;
import com.zhangyun.portalknight.server.domain.model.User;
import com.zhangyun.portalknight.server.domain.req.DeviceInitReq;
import com.zhangyun.portalknight.server.domain.req.DeviceReq;
import com.zhangyun.portalknight.server.domain.mapper.DeviceMapper;
import com.zhangyun.portalknight.server.domain.response.Response;
import com.zhangyun.portalknight.server.domain.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
@Service
@Slf4j
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DeviceConvert deviceConvert;

    @Override
    public Response deviceInit(DeviceInitReq req) {
        User user = userService.findUserByEmail(req.getEmail());
        // gen UUID
        UUID uuid = UUID.randomUUID();
        Device device = new Device();
        device.setDeviceId(uuid.toString());
        device.setDeviceDesc(req.getDeviceDesc());
        device.setUserid(user.getUserid());
        device.setIsOnline(false);
        int result = deviceMapper.insert(device);
        if (result <= 0) {
            log.info("设备初始化失败: {}", req);
            return Response.fail(400, "设备初始化失败");
        } else {
            log.info("设备初始化成功: {}", req);
            return Response.ok(200, "设备初始化成功", "deviceid", uuid);
        }
    }

    @Override
    public Response isOnline(String deviceid) {
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("deviceid", deviceid);
        Device device = deviceMapper.selectOne(wrapper);
        if (ObjectUtil.equal(device.getIsOnline(), 1)) {
            log.info("getStatusByDeviceId, 设备在线: {}", deviceid);
            return Response.ok(200, "设备在线", "token", device.getToken());
        } else {
            log.info("getStatusByDeviceId, 设备不在线: {}", deviceid);
            return Response.ok(200, "设备不在线", "isOnline", false);
        }
    }

    @Override
    public Response updataStatus(DeviceReq req) {
        Device device = deviceConvert.deviceReqToDevice(req);
        int result = deviceMapper.updateById(device);
        if (result <= 0) {
            log.error("更新失败: {}", req);
            return Response.fail();
        } else {
            log.info("更新成功: {}", req);
            return Response.ok();
        }
    }
}
