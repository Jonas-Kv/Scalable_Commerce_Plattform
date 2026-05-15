package com.jonaskv.ecommerce.user_service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRequest {

  private String street;
  private String city;
  private String postalCode;
  private String country;
  private Boolean isDefault;
}
