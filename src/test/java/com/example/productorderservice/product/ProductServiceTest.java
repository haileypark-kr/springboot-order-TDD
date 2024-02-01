package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.dto.GetProductResponse;
import com.example.productorderservice.service.ProductService;

@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	@Transactional
	void 상품등록() throws Exception {
		final AddProductRequest request = ProductTestSteps.상품등록요청_생성();

		productService.addProduct(request);
	}

	@Test
	@Transactional
	void 상품조회() throws Exception {
		// given - 상품 등록
		productService.addProduct(ProductTestSteps.상품등록요청_생성());
		final Long productId = 1L;

		// when - 상품 조회
		final GetProductResponse response = productService.getProduct(productId);

		// then - 상품 응답 검증
		assertThat(response).isNotNull();
	}
}
