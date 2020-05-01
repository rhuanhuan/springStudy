package com.hruan.springcloud.controller;

import com.hruan.springcloud.entities.CommonResult;
import com.hruan.springcloud.entities.Payment;
import com.hruan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value="/payments")
    public CommonResult create(@RequestBody Payment payment) {
        long result = paymentService.create(payment);
        log.info("**Insert Payment: " + result);

        if (result > 0) {
            return new CommonResult(200, "Insert Payment Success from port: " + serverPort, result);
        } else {
            return new CommonResult(400, "Insert Payment failed from port: " + serverPort, null);
        }
    }

    @GetMapping(value="/payments/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**Get Payment: " + payment);

        if (payment != null) {
            return new CommonResult(200, "Get Payment Success from port: " + serverPort, payment);
        } else {
            return new CommonResult(404, "Payment not found from port: " + serverPort, null);
        }
    }

    @GetMapping(value = "/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();

        for (String element : services) {
            log.info("**** element: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId());
        }

        return this.discoveryClient;
    }
}
