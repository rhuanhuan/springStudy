package com.hruan.springcloud.service;

import com.hruan.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    long create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
