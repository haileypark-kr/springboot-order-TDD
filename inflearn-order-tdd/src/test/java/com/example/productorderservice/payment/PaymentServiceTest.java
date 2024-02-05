package com.example.productorderservice.payment;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productorderservice.dto.PaymentRequest;
import com.example.productorderservice.service.PaymentService;

@SpringBootTest
public class PaymentServiceTest {

	@Autowired
	private PaymentService paymentService;

	@Test
	@Transactional
	public void 상품주문() throws Exception {
		// given
		final PaymentRequest request = PaymentTestSteps.주문결제요청_생성();
		paymentService.pay(request);

		// when

		// then
	}

}
