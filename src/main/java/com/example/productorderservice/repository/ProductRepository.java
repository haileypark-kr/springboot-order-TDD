package com.example.productorderservice.repository;

import java.util.HashMap;
import java.util.Map;

import com.example.productorderservice.model.Product;

public class ProductRepository {
	private Map<Long, Product> persistence = new HashMap<>();
	private Long seq = 0L;

	public void save(final Product product) {
		product.assignId(++seq);
		persistence.put(product.getId(), product);
	}
}
