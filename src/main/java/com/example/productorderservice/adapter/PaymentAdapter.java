package com.example.productorderservice.adapter;

import org.springframework.stereotype.Component;

import com.example.productorderservice.gateway.PaymentGateway;
import com.example.productorderservice.model.Order;
import com.example.productorderservice.model.Payment;
import com.example.productorderservice.model.Product;
import com.example.productorderservice.port.PaymentPort;
import com.example.productorderservice.repository.PaymentRepository;
import com.example.productorderservice.type.DiscountPolicy;

@Component
public class PaymentAdapter implements PaymentPort {

	private final PaymentGateway paymentGateway;
	private final PaymentRepository paymentRepository;

	private PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
		this.paymentGateway = paymentGateway;
		this.paymentRepository = paymentRepository;
	}

	@Override
	public Order getOrder(Long orderId) {
		return new Order(new Product("상품1", 1000, DiscountPolicy.NONE), 2);
	}

	@Override
	public void pay(Payment payment) {
		paymentGateway.execute(payment);
	}

	@Override
	public void save(Payment payment) {
		paymentRepository.save(payment);
	}

}
