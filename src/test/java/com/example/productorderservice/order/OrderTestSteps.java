package com.example.productorderservice.order;

import com.example.productorderservice.dto.CreateOrderRequest;

public class OrderTestSteps {

	public static CreateOrderRequest 상품주문요청_생성() {
		final Long productId = 1L;
		final int quantity = 2;
		return new CreateOrderRequest(productId, quantity);
	}
	//
	// public static ExtractableResponse<Response> 상품등록_요청(AddProductRequest request) {
	// 	return RestAssured
	// 		.given().log().all() // 요청 로그를 남기겠다. Request method, URI, params, headers, body 등.
	// 		.contentType(MediaType.APPLICATION_JSON_VALUE)
	// 		.body(request)
	// 		.when()
	// 		.post("/products")
	// 		.then().log().all() // 응답 로그를 남기겠다. HTTP 코드, Content-Length 등.
	// 		.extract();
	// }
	//
	// public static ExtractableResponse<Response> 상품조회_요청(Long id) {
	// 	ExtractableResponse<Response> response = RestAssured
	// 		.given().log().all()
	// 		.contentType(MediaType.APPLICATION_JSON_VALUE)
	// 		.when()
	// 		.get("/products/" + id)
	// 		.then().log().all()
	// 		.extract();
	// 	return response;
	// }
	//
	// public static ExtractableResponse<Response> 상품수정_요청(Long id, UpdateProductRequest request) {
	// 	ExtractableResponse<Response> response = RestAssured
	// 		.given().log().all()
	// 		.contentType(MediaType.APPLICATION_JSON_VALUE)
	// 		.body(request)
	// 		.when()
	// 		.post("/products/" + id)
	// 		.then().log().all()
	// 		.extract();
	// 	return response;
	// }
}
