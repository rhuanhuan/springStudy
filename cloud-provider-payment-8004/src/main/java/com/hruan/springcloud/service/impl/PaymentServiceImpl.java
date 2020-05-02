package com.hruan.springcloud.service.impl;

import com.hruan.springcloud.dao.PaymentDao;
import com.hruan.springcloud.entities.Payment;
import com.hruan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public long create(Payment payment) {
        paymentDao.create(payment);
        return payment.getId();
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
