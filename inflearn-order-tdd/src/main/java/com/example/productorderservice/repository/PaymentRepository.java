package com.example.productorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productorderservice.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
