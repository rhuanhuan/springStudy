package com.hruan.springcloud.controller;

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

    @GetMapping("/consumers/payments/{id}/ok")
    public String getPaymentOK(@PathVariable("id") Long id) {
        String result = paymentFeignService.getPaymentOK(id);
        log.info(result);
        return result;
    }

    @GetMapping("/consumers/payments/{id}/timeout")
    public String getPaymentTimeout(@PathVariable("id") Long id) {
        String result = paymentFeignService.getPaymentTimeOut(id);
        log.info(result);

        return result;
    }
}
