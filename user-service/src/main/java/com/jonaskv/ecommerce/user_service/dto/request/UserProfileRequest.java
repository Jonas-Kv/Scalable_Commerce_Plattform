package com.jonaskv.ecommerce.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserProfileRequest {
  
  public String firstName;
  public String lastName;
  public String phoneNumber;
}
