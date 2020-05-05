package com.hruan.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfoOK(Integer id){
        return "Thread Pool: " + Thread.currentThread().getName() + "paymentInfoOK, id: " + id;
    }

    public String paymentInfoTimeOut(Integer id){
        int timeout = 5;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Thread Pool: " + Thread.currentThread().getName() + "paymentInfoTimeOut, id: " + id;
    }
}
