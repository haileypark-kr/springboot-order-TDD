package com.example.productorderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.dto.GetProductResponse;
import com.example.productorderservice.model.Product;
import com.example.productorderservice.port.ProductPort;

@Service
@Transactional(readOnly = true)
public class ProductService {
	private final ProductPort productPort;

	public ProductService(ProductPort productPort) {
		this.productPort = productPort;
	}

	@Transactional
	public void addProduct(AddProductRequest request) {
		Product product = new Product(request.name(), request.price(), request.discountPolicy());
		productPort.save(product);
	}

	public GetProductResponse getProduct(Long productId) {
		final Product product = productPort.getProduct(productId);
		return new GetProductResponse(product.getId(),
			product.getName(),
			product.getPrice(),
			product.getDiscountPolicy());
	}
}
