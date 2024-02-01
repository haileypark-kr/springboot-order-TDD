package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.service.ProductService;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class ProductApiTest extends ApiTest {

	@Autowired
	private ProductService productService;

	@Test
	@Transactional
	void 상품등록() {
		final AddProductRequest request = ProductTestSteps.상품등록요청_생성();

		final ExtractableResponse<Response> response = ProductTestSteps.상품등록_요청(request);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	@Transactional
	public void 상품조회() throws Exception {
		// given - 상품 등록
		ProductTestSteps.상품등록_요청(ProductTestSteps.상품등록요청_생성());
		Long id = 1L;

		// when - 상품 조회
		ExtractableResponse<Response> response = ProductTestSteps.상품조회_요청(id);

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
	}

}
