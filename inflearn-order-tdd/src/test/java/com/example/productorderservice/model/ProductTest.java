package com.example.productorderservice.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.productorderservice.type.DiscountPolicy;

class ProductTest {

	@Test
	void update() {
		Product product = new Product("상품명", 1000, DiscountPolicy.NONE);

		product.update("상품명 수정", 2000, DiscountPolicy.NONE);

		assertThat(product.getName()).isEqualTo("상품명 수정");
		assertThat(product.getPrice()).isEqualTo(2000);
	}
}
