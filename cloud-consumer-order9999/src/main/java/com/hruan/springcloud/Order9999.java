package com.hruan.springcloud;

import com.hruan.myrule.CRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = CRule.class)
public class Order9999 {
    public static void main(String[] args) {
        SpringApplication.run(Order9999.class, args);
    }
}
