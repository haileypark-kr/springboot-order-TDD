package com.example.productorderservice.port;

import com.example.productorderservice.model.Order;
import com.example.productorderservice.model.Payment;

public interface PaymentPort {
	Order getOrder(Long orderId);

	void pay(Payment payment);

	void save(Payment payment);
}
