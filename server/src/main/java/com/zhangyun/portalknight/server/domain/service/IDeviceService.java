package com.zhangyun.portalknight.server.domain.service;

import com.zhangyun.portalknight.server.domain.req.DeviceReq;
import com.zhangyun.portalknight.server.domain.model.Device;
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
    public Response deviceInit(DeviceReq req);

    public Response isOnline(String deviceid);

    public Response updataStatus(DeviceReq req);
}
