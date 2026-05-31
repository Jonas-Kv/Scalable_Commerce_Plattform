package com.jonaskv.ecommerce.auth_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
  
  private String token;
  private Long userId;
  private String email;
  private String role;
}
