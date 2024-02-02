package com.example.productorderservice.order;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productorderservice.product.ProductTestSteps;
import com.example.productorderservice.service.OrderService;
import com.example.productorderservice.service.ProductService;

@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Test
	@Transactional
	public void 상품주문() throws Exception {
		// given
		productService.addProduct(ProductTestSteps.상품등록요청_생성());

		orderService.createOrder(OrderTestSteps.상품주문요청_생성());

	}

}
