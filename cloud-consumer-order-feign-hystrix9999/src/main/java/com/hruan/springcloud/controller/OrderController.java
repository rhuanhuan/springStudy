package com.hruan.springcloud.controller;

import com.hruan.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentGlobalFallBack")
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumers/payments/{id}/ok")
    public String getPaymentOK(@PathVariable("id") Integer id) {
        String result = paymentFeignService.getPaymentOK(id);
        log.info(result);
        return result;
    }

    @GetMapping("/consumers/payments/{id}/timeout")
//    @HystrixCommand(fallbackMethod = "paymentInfoFallBack", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
//    })
//    @HystrixCommand
    public String getPaymentTimeout(@PathVariable("id") Integer id) {
        String result = paymentFeignService.getPaymentTimeOut(id);
        log.info(result);

        return result;
    }

    public String paymentInfoFallBack(@PathVariable("id") Integer id) {
        return "I am Order. Payment Busy, please try it again later. >>> Thread Pool: " + Thread.currentThread().getName() + ". paymentInfoFallBack, id: " + id;
    }

    public String paymentGlobalFallBack() {
        return "Order Busy, please try it again later";
    }
}
