package com.example.productorderservice.adapter;

import com.example.productorderservice.model.Product;
import com.example.productorderservice.port.ProductPort;
import com.example.productorderservice.repository.ProductRepository;

public class ProductAdapter implements ProductPort {
	private final ProductRepository productRepository;

	public ProductAdapter(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

}
