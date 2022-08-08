package com.zhangyun.portalknight.server.domain.convert;

import com.zhangyun.portalknight.server.domain.model.Device;
import com.zhangyun.portalknight.server.domain.model.User;
import com.zhangyun.portalknight.server.domain.req.DeviceReq;
import com.zhangyun.portalknight.server.domain.req.LoginReq;
import com.zhangyun.portalknight.server.domain.req.UserReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/8 00:45
 * @since: 1.0
 */
@Mapper(componentModel = "spring")
public interface DeviceConvert {
    DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);

    DeviceReq deviceToDeviceReq(Device device);

    Device deviceReqToDevice(DeviceReq deviceReq);

    DeviceReq loginReqToDeviceReq(LoginReq loginReq);
}
