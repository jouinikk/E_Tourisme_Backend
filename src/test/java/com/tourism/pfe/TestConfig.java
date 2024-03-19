package com.tourism.pfe;

import com.tourism.pfe.Config.JWTService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public JWTService jwtService() {
        return new JWTService();
    }
}
