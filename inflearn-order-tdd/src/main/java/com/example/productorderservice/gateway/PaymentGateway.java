package com.example.productorderservice.gateway;

import org.springframework.stereotype.Component;

import com.example.productorderservice.model.Payment;

@Component
public class PaymentGateway {
	public void execute(Payment payment) {
		System.out.println("결제 성공");
	}
}
