package com.zhangyun.portalknight.server.domain.service;

import com.zhangyun.portalknight.server.domain.model.Device;
import com.zhangyun.portalknight.server.domain.req.DeviceInitReq;
import com.zhangyun.portalknight.server.domain.req.DeviceReq;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangyun.portalknight.server.domain.response.Response;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangyun
 * @since 2022-08-07
 */
public interface IDeviceService extends IService<Device> {
    public Response deviceInit(DeviceInitReq req);

    public Response isOnline(String deviceId);

    public Response updataStatus(DeviceReq req);

    public Response getDeviceById(String deviceId);
}
