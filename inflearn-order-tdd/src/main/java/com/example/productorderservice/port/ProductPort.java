package com.example.productorderservice.port;

import com.example.productorderservice.model.Product;

public interface ProductPort {
	void save(Product product);

	Product getProduct(Long productId);
	
}
