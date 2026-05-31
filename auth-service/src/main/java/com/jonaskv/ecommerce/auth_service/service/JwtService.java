package com.jonaskv.ecommerce.auth_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  
  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private Long expiration;
}
