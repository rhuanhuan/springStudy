package com.hruan.springcloud.controller;

import com.hruan.springcloud.entities.CommonResult;
import com.hruan.springcloud.entities.Payment;
import com.hruan.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumers/payments/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        CommonResult<Payment> payment = paymentFeignService.getPaymentById(id);
        return payment;
    }

    @GetMapping(value="/consumers/test-timeout")
    public String testTimeOut(){
        return paymentFeignService.testTimeOut();
    }
}
