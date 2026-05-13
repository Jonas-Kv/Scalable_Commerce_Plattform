package com.jonaskv.ecommerce.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
  
  private Long id;
  private String street;
  private String city;
  private String postalCode;
  @Builder.Default
  private String country = "Germamy";
  private Boolean isDefault;
}
