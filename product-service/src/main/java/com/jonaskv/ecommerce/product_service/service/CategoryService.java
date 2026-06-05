package com.jonaskv.ecommerce.product_service.service;

import org.springframework.stereotype.Service;

import com.jonaskv.ecommerce.product_service.dto.request.CategoryRequest;
import com.jonaskv.ecommerce.product_service.dto.response.CategoryResponse;
import com.jonaskv.ecommerce.product_service.entity.Category;
import com.jonaskv.ecommerce.product_service.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  
  private final CategoryRepository categoryRepository;

  public CategoryResponse createCategory(CategoryRequest request) {
    Category category = Category.builder()
        .name(request.getName())
        .description(request.getDescription())
        .build();

    categoryRepository.save(category);

    return CategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .build();
  }
}
