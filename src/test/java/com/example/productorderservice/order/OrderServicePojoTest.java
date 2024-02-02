package com.example.productorderservice.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.example.productorderservice.model.Product;
import com.example.productorderservice.repository.ProductRepository;
import com.example.productorderservice.type.DiscountPolicy;

@ExtendWith(MockitoExtension.class)
public class OrderServicePojoTest {

	private OrderService orderService;
	private OrderPort orderAdapter;
	private OrderRepository orderRepository;
	private ProductRepository productRepository;

	@BeforeEach
	void setUp() {

		productRepository = Mockito.mock(ProductRepository.class);
		Mockito.when(productRepository.findById(1L))
			.thenReturn(Optional.of(new Product("상품명", 2000, DiscountPolicy.NONE)));

		orderRepository = new OrderRepository();
		orderAdapter = new OrderAdapter(productRepository, orderRepository);
		orderService = new OrderService(orderAdapter);
	}

	@Test
	@Transactional
	public void 상품주문() throws Exception {
		// given

		final Long productId = 1L;
		final int quantity = 2;
		final CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
		orderService.createOrder(request);

		// when

		// then
	}

	private record CreateOrderRequest(Long productId, int quantity) {
		private CreateOrderRequest {
			Assert.notNull(productId, "상품 ID는 필수입니다.");
			Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
		}
	}

	private class OrderService {
		private final OrderPort orderPort;

		private OrderService(OrderPort orderPort) {
			this.orderPort = orderPort;
		}

		public void createOrder(CreateOrderRequest request) {
			Product product = orderPort.getProductById(request.productId());
			final Order order = new Order(product, request.quantity());
			orderPort.save(order);
		}
	}

	private class OrderAdapter implements OrderPort {
		private final ProductRepository productRepository;
		private final OrderRepository orderRepository;

		private OrderAdapter(ProductRepository productRepository, OrderRepository orderRepository) {
			this.productRepository = productRepository;
			this.orderRepository = orderRepository;
		}

		@Override
		public Product getProductById(Long productId) {
			return productRepository.findById(productId).orElseThrow(IllegalStateException::new);
		}

		@Override
		public void save(Order order) {
			orderRepository.save(order);
		}
	}

	private interface OrderPort {
		Product getProductById(Long productId);

		void save(Order order);
	}

	private class Order {
		private Long id;
		private final Product product;
		private final int quantity;

		public Order(Product product, int quantity) {
			this.product = product;
			this.quantity = quantity;
			Assert.notNull(product, "상품은 필수입니다.");
			Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
		}

		public Long getId() {
			return id;
		}

		public void assignId(Long id) {
			this.id = id;
		}
	}

	private class OrderRepository {
		private Map<Long, Order> persistence = new HashMap<>();

		private Long seq = 0L;

		public void save(Order order) {
			order.assignId(++seq);
			persistence.put(order.getId(), order);
		}
	}

}
