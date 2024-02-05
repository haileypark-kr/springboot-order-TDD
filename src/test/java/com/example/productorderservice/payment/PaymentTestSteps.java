package com.example.productorderservice.payment;

import com.example.productorderservice.dto.PaymentRequest;

public class PaymentTestSteps {
	public static PaymentRequest 주문결제요청_생성() {
		Long orderId = 1L;
		String cardNumber = "1234-1234-5555-6666";
		final PaymentRequest request = new PaymentRequest(orderId, cardNumber);
		return request;
	}
}

