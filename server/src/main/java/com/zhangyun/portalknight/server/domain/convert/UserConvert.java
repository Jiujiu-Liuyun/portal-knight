package com.zhangyun.portalknight.server.domain.convert;

import com.zhangyun.portalknight.server.domain.req.UserReq;
import com.zhangyun.portalknight.server.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 19:42
 * @since: 1.0
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserReq userToUserDTO(User user);

    User userDTOToUser(UserReq userReq);
}
