package com.coderhouse.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class ApplicationProperties {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port}")
    private Integer port;
    @Value(value = "${spring.redis.timeOfLife}")
    private Integer timeOfLife;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int expiration;

}