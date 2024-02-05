package com.example.productorderservice.port;

import com.example.productorderservice.model.Order;
import com.example.productorderservice.model.Product;

public interface OrderPort {
	Product getProductById(Long productId);

	void save(Order order);

}
