package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.service.ProductService;
import com.example.productorderservice.type.DiscountPolicy;

@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	void 상품등록() {
		final AddProductRequest request = 상품등록요청_생성();

		productService.addProduct(request);
	}

	// API 요청

	private static AddProductRequest 상품등록요청_생성() {

		String name = "상품명";
		int price = 10000;
		DiscountPolicy discountPolicy = DiscountPolicy.NONE;

		return new AddProductRequest(name, price, discountPolicy);
	}

}
