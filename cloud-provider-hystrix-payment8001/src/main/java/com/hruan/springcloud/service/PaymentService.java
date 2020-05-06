package com.hruan.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfoOK(Integer id){
        return "Thread Pool: " + Thread.currentThread().getName() + "paymentInfoOK, id: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeOut(Integer id){
        int timeout = 5;
        try { TimeUnit.SECONDS.sleep(timeout); } catch (InterruptedException e) { e.printStackTrace();}

        return "Thread Pool: " + Thread.currentThread().getName() + ". paymentInfoTimeOut, id: " + id;
    }

    public String paymentInfoTimeOutHandler(Integer id){
        return "Thread Pool: " + Thread.currentThread().getName() + ". paymentInfoTimeOutHandler, id: " + id;
    }
}
