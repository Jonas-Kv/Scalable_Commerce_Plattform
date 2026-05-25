package com.jonaskv.ecommerce.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

  private String street;
  private String city;
  private String postalCode;
  private String country;
  private Boolean isDefault;
}
