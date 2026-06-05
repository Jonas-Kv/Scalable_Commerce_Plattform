package com.jonaskv.ecommerce.product_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonaskv.ecommerce.product_service.dto.request.CategoryRequest;
import com.jonaskv.ecommerce.product_service.dto.response.CategoryResponse;
import com.jonaskv.ecommerce.product_service.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  
  @PostMapping
  public ResponseEntity<CategoryResponse> createCategory(
      @RequestBody CategoryRequest request
  ) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoryService.createCategory(request));
  }
}
