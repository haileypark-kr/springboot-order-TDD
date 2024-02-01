package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productorderservice.dto.UpdateProductRequest;
import com.example.productorderservice.model.Product;
import com.example.productorderservice.port.ProductPort;
import com.example.productorderservice.service.ProductService;
import com.example.productorderservice.type.DiscountPolicy;

@ExtendWith(MockitoExtension.class) // Mockito 익스텐션 어노테이션 추가
public class ProductUpdatePojoTest {

	private ProductService productService;
	private ProductPort productPort;

	@BeforeEach
	void setUp() {
		productPort = Mockito.mock(ProductPort.class); // ProductPort 클래스로 mock 만들기.
		productService = new ProductService(this.productPort);
	}

	@Test
	@Transactional
	public void 상품수정() throws Exception {
		final Long productId = 1L;
		final UpdateProductRequest request = new UpdateProductRequest("상품명수정", 2000, DiscountPolicy.NONE);

		// 1.
		final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
		Mockito.when(productPort.getProduct(productId)).thenReturn(product);

		// 2.
		// Mockito.when(productPort.getProduct(productId)).thenAnswer(new Answer<Product>() {
		// 	@Override
		// 	public Product answer(InvocationOnMock invocation) throws Throwable {
		// 		// 리턴할 객체에 대한 조건/operation ~~~
		// 		return new Product("상품명", 1000, DiscountPolicy.NONE);
		// 	}
		// });

		productService.updateProduct(productId, request);

		assertThat(product.getName()).isEqualTo("상품명수정");
		assertThat(product.getPrice()).isEqualTo(2000);
	}
}
