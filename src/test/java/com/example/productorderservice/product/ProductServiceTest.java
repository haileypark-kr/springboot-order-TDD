package com.example.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.productorderservice.adapter.ProductAdapter;
import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.port.ProductPort;
import com.example.productorderservice.repository.ProductRepository;
import com.example.productorderservice.service.ProductService;
import com.example.productorderservice.type.DiscountPolicy;

class ProductServiceTest {

	private ProductService productService;
	private ProductPort productPort;
	private ProductRepository productRepository;

	@BeforeEach
	void setUp() {
		productRepository = new ProductRepository();
		productPort = new ProductAdapter(productRepository);
		productService = new ProductService(productPort);
	}

	@Test
	void 상품등록() {
		final AddProductRequest request = 상품등록요청_생성();
		
		productService.addProduct(request);
	}

	private static AddProductRequest 상품등록요청_생성() {

		String name = "상품명";
		int price = 10000;
		DiscountPolicy discountPolicy = DiscountPolicy.NONE;

		return new AddProductRequest(name, price, discountPolicy);
	}

}
