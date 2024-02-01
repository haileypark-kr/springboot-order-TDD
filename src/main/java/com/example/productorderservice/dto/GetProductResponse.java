package com.example.productorderservice.dto;

import org.springframework.util.Assert;

import com.example.productorderservice.type.DiscountPolicy;

public record GetProductResponse(Long id, String name, int price, DiscountPolicy discountPolicy) {
	public GetProductResponse {
		Assert.notNull(id, "상품 ID는 필수입니다.");
		Assert.hasText(name, "상품명은 필수입니다.");
		Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
	}
}
