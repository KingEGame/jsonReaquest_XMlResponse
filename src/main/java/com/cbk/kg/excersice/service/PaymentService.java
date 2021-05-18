package com.cbk.kg.excersice.service;

import com.cbk.kg.excersice.entity.Payment;
import com.cbk.kg.excersice.model.request.Check;
import com.cbk.kg.excersice.respository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment save(Payment payment){
        return paymentRepository.saveAndFlush(payment);
    }

    public Payment getByRequestId(Long requestId){
        return paymentRepository.getFirstByRequestId(requestId);
    }
}
