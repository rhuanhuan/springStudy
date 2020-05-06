package com.hruan.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentFeignService{
    @Override
    public String getPaymentOK(Integer id) {
        return "**PaymentFallbackService fall back";
    }

    @Override
    public String getPaymentTimeOut(Integer id) {
        return "**PaymentFallbackService fall back";
    }
}
