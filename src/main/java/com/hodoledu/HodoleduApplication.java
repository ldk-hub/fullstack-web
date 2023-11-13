package com.hodoledu;

import com.hodoledu.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class HodoleduApplication {

    public static void main(String[] args) {
        SpringApplication.run(HodoleduApplication.class, args);
    }

}
