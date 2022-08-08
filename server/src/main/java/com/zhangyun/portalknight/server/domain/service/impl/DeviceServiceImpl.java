package com.zhangyun.portalknight.server.domain.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangyun.portalknight.server.domain.convert.DeviceConvert;
import com.zhangyun.portalknight.server.domain.req.DeviceReq;
import com.zhangyun.portalknight.server.domain.model.Device;
import com.zhangyun.portalknight.server.domain.mapper.DeviceMapper;
import com.zhangyun.portalknight.server.domain.response.Response;
import com.zhangyun.portalknight.server.domain.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    private DeviceConvert deviceConvert;

    @Override
    public Response deviceInit(DeviceReq req) {

        // UUID
        UUID uuid = UUID.randomUUID();


        return Response.ok();
    }

    @Override
    public Response isOnline(String deviceid) {
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("deviceid", deviceid);
        Device devices = deviceMapper.selectOne(wrapper);
        if (ObjectUtil.equal(devices.getIsOnline(), 1)) {
            log.info("getStatusByDeviceId, 设备在线: {}", deviceid);
            return Response.ok(200, "设备在线", true);
        } else {
            log.info("getStatusByDeviceId, 设备不在线: {}", deviceid);
            return Response.ok(200, "设备不在线", false);
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
