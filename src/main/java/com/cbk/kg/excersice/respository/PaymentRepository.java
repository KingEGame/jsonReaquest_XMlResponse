package com.cbk.kg.excersice.respository;

import com.cbk.kg.excersice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment getFirstByRequestId(Long requestId);
}
