package com.example.productorderservice.service;

import org.springframework.stereotype.Service;

import com.example.productorderservice.dto.CreateOrderRequest;
import com.example.productorderservice.model.Order;
import com.example.productorderservice.model.Product;
import com.example.productorderservice.port.OrderPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderPort orderPort;

	public void createOrder(CreateOrderRequest request) {
		Product product = orderPort.getProductById(request.productId());
		final Order order = new Order(product, request.quantity());
		orderPort.save(order);
	}
}
