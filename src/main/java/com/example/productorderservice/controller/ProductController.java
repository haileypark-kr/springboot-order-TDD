package com.example.productorderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productorderservice.dto.AddProductRequest;
import com.example.productorderservice.dto.GetProductResponse;
import com.example.productorderservice.dto.UpdateProductRequest;
import com.example.productorderservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody AddProductRequest request) {

		productService.addProduct(request);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{productId}")
	public ResponseEntity<GetProductResponse> get(@PathVariable Long productId) {

		GetProductResponse response = productService.getProduct(productId);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/{productId}")
	public ResponseEntity<Void> update(@PathVariable final Long productId,
		@RequestBody final UpdateProductRequest request) {

		productService.updateProduct(productId, request);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
