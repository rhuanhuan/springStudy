package com.hruan.springcloud.controller;

import com.hruan.springcloud.entities.CommonResult;
import com.hruan.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumers/payments")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payments", payment, CommonResult.class );
    }

    @GetMapping("/consumers/payments/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        log.info("**** Get Payment by id: " + id);
        return restTemplate.getForObject(PAYMENT_URL + "/payments/" + id, CommonResult.class );
    }
}
