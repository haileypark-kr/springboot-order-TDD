package com.example.productorderservice.adapter;

import org.springframework.stereotype.Component;

import com.example.productorderservice.model.Order;
import com.example.productorderservice.model.Product;
import com.example.productorderservice.port.OrderPort;
import com.example.productorderservice.repository.OrderRepository;
import com.example.productorderservice.repository.ProductRepository;

@Component
public class OrderAdapter implements OrderPort {
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;

	public OrderAdapter(ProductRepository productRepository, OrderRepository orderRepository) {
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElseThrow(IllegalStateException::new);
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}
}
