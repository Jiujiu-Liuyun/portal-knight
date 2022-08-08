package com.zhangyun.portalknight.server.domain.convert;

import com.zhangyun.portalknight.server.ServerApplicationTests;
import com.zhangyun.portalknight.server.domain.req.UserReq;
import com.zhangyun.portalknight.server.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class UserConvertTest extends ServerApplicationTests {

    @Autowired
    private UserConvert userConvert;

    @Test
    void userToUserDTOTest() {
        User user = new User();
        user.setUsername("zhangyun");
        user.setUserid(1);
        UserReq userReq = userConvert.userToUserDTO(user);
        log.info("{}", userReq);
        User user1 = userConvert.userDTOToUser(userReq);
        log.info("{}", user1);
    }

    @Test
    void testUserToUserDTO() {
    }
}