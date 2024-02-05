package com.example.productorderservice.service;

import org.springframework.stereotype.Service;

import com.example.productorderservice.dto.PaymentRequest;
import com.example.productorderservice.model.Order;
import com.example.productorderservice.model.Payment;
import com.example.productorderservice.port.PaymentPort;

@Service
public class PaymentService {
	private final PaymentPort paymentPort;

	public PaymentService(PaymentPort paymentPort) {
		this.paymentPort = paymentPort;
	}

	public void pay(PaymentRequest request) {
		Order order = paymentPort.getOrder(request.orderId());

		final Payment payment = new Payment(order, request.cardNumber());

		paymentPort.pay(payment);
		paymentPort.save(payment);
	}
}
