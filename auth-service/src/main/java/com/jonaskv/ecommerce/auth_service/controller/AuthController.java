package com.jonaskv.ecommerce.auth_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonaskv.ecommerce.auth_service.dto.request.LoginRequest;
import com.jonaskv.ecommerce.auth_service.dto.request.RegisterRequest;
import com.jonaskv.ecommerce.auth_service.dto.response.AuthResponse;
import com.jonaskv.ecommerce.auth_service.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authservice;
  
  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(
      @Valid @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authservice.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(
      @RequestBody LoginRequest request
  ) {
    return ResponseEntity.ok(authservice.login(request));
  }
}
