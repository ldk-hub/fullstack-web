package com.hodoledu.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Base64;

@Data
@ConfigurationProperties(prefix = "hodolman")
public class AppConfig {

    private byte[] jwtKey;

    public void setJwtKey(String jwtKey) {
       this.jwtKey =  Base64.getDecoder().decode(jwtKey);
    }

    public byte[] getJwtKey() {
        return jwtKey;
    }
}
