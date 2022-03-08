package com.example.config;

import com.example.client.UserClientFallbackFactory;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public UserClientFallbackFactory userClientFallbackFactory() {
        return new UserClientFallbackFactory();
    }

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

}
