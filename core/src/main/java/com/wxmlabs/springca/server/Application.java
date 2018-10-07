package com.wxmlabs.springca.server;

import com.wxmlabs.springca.server.conf.SpringCAConf;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Security;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        System.setProperty("spring.config.name", "springca");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConfigurationProperties("springca")
    public SpringCAConf conf() {
        return new SpringCAConf();
    }
}
