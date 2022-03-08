package com.example.client;

import com.example.dto.UserDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable throwable) {
        return x -> {
            log.error("查询用户异常", throwable);
            return new UserDTO();
        };
    }

}

