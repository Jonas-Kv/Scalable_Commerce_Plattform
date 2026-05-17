package com.jonaskv.ecommerce.user_service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSummary {
  
  private Long id;
  private String firstName;
  private String lastName;
}
