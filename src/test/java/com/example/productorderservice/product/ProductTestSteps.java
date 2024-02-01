package com.example.productorderservice.product;

import org.springframework.http.MediaType;

import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.type.DiscountPolicy;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductTestSteps {
	public static AddProductRequest 상품등록요청_생성() {

		String name = "상품명";
		int price = 10000;
		DiscountPolicy discountPolicy = DiscountPolicy.NONE;

		return new AddProductRequest(name, price, discountPolicy);
	}

	public static ExtractableResponse<Response> 상품등록_요청(AddProductRequest request) {
		return RestAssured
			.given().log().all() // 요청 로그를 남기겠다. Request method, URI, params, headers, body 등.
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when()
			.post("/products")
			.then().log().all() // 응답 로그를 남기겠다. HTTP 코드, Content-Length 등.
			.extract();
	}

	public static ExtractableResponse<Response> 상품조회_요청(Long id) {
		ExtractableResponse<Response> response = RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.when()
			.get("/products/" + id)
			.then().log().all()
			.extract();
		return response;
	}
}
