package com.jonaskv.ecommerce.product_service.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
  
  private String name;
  private String description;
  private BigDecimal price;
  private Long stock;
  private Long categoryId;
  private Boolean isAvailable;
}
