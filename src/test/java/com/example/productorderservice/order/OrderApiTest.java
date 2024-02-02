package com.example.productorderservice.order;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.dto.CreateOrderRequest;
import com.example.productorderservice.product.ProductTestSteps;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class OrderApiTest extends ApiTest {

	@Test
	@Transactional
	public void 상품주문() throws Exception {
		// given
		ProductTestSteps.상품등록_요청(ProductTestSteps.상품등록요청_생성());

		final CreateOrderRequest orderRequest = OrderTestSteps.상품주문요청_생성();

		// when
		ExtractableResponse<Response> response = OrderTestSteps.상품주문_요청(orderRequest);

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
