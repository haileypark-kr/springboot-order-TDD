package com.example.productorderservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ORDER_PRODUCT")
	private Product product;
	private int quantity;

	public Order(Product product, int quantity) {
		Assert.notNull(product, "상품은 필수입니다.");
		Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
		this.product = product;
		this.quantity = quantity;
	}

}
