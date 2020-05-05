package com.hruan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignHystrix9999 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrix9999.class, args);
    }
}
