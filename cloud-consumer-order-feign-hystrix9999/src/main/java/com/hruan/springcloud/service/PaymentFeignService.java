package com.hruan.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value="/payments/{id}/ok")
    String getPaymentOK(@PathVariable("id") Integer id);

    @GetMapping(value="/payments/{id}/timeout")
    String getPaymentTimeOut(@PathVariable("id") Integer id);
}
