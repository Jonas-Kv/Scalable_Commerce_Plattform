package com.jonaskv.ecommerce.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonaskv.ecommerce.product_service.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{
  
}
