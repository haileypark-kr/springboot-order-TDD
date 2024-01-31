package com.example.productorderservice.service;

import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.model.Product;
import com.example.productorderservice.port.ProductPort;

public class ProductService {
	private final ProductPort productPort;

	public ProductService(ProductPort productPort) {
		this.productPort = productPort;
	}

	public void addProduct(AddProductRequest request) {
		Product product = new Product(request.name(), request.price(), request.discountPolicy());
		productPort.save(product);
	}
}
