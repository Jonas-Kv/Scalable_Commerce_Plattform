package com.jonaskv.ecommerce.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
  
  private Long id;
  private String street;
  private String city;
  private String postalCode;
  @Builder.Default
  private String country = "Germany";
  private Boolean isDefault;
}
