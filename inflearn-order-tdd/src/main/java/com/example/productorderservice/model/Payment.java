package com.example.productorderservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FK_PAYMENT_ORDER")
	private Order order;
	private String cardNumber;

	public Payment(Order order, String cardNumber) {
		this.order = order;
		this.cardNumber = cardNumber;
	}

	public Long getId() {
		return id;
	}

	public int getPrice() {
		return order.getTotalPrice();
	}
}
