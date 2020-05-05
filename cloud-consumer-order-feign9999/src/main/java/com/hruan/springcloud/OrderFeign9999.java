package com.hruan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeign9999 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeign9999.class, args);
    }
}
