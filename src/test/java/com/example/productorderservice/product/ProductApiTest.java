package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.service.ProductService;
import com.example.productorderservice.type.DiscountPolicy;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class ProductApiTest extends ApiTest {

	@Autowired
	private ProductService productService;

	@Test
	void 상품등록() {
		final AddProductRequest request = 상품등록요청_생성();

		// API 요청
		final ExtractableResponse<Response> response = 상품등록_요청(request);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private static ExtractableResponse<Response> 상품등록_요청(AddProductRequest request) {
		return RestAssured
			.given().log().all() // 요청 로그를 남기겠다. Request method, URI, params, headers, body 등.
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when()
			.post("/products")
			.then().log().all() // 응답 로그를 남기겠다. HTTP 코드, Content-Length 등.
			.extract();
	}

	// API 요청

	private static AddProductRequest 상품등록요청_생성() {

		String name = "상품명";
		int price = 10000;
		DiscountPolicy discountPolicy = DiscountPolicy.NONE;

		return new AddProductRequest(name, price, discountPolicy);
	}

}
