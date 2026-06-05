package com.jonaskv.ecommerce.product_service.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Summary for other services
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummaryDto {
  
  private Long id;
  private BigDecimal price;
  private String imageUrl;
  private Long Stock;
}
