package com.hruan.springcloud.controller;

import com.hruan.springcloud.entities.CommonResult;
import com.hruan.springcloud.entities.Payment;
import com.hruan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value="/payments")
    public CommonResult create(@RequestBody Payment payment) {
        long result = paymentService.create(payment);
        log.info("**Insert Payment: " + result);

        if (result > 0) {
            return new CommonResult(200, "Insert Payment Success", result);
        } else {
            return new CommonResult(400, "Insert Payment failed", null);
        }
    }

    @GetMapping(value="/payments/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**Get Payment: " + payment);

        if (payment != null) {
            return new CommonResult(200, "Get Payment Success", payment);
        } else {
            return new CommonResult(404, "Payment not found", null);
        }
    }
}
