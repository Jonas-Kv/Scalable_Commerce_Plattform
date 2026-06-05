package com.jonaskv.ecommerce.product_service.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
  
  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private Long stock;
  private Long categoryId;
  private Boolean isAvailable;
  private String categoryName;
  List<String> imageUrlList;
}
