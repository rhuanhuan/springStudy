package com.hruan.officialdemo;

import com.hruan.officialdemo.config.UriConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);

    }
}