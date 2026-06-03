package com.jonaskv.ecommerce.auth_service.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jonaskv.ecommerce.auth_service.client.UserServiceClient;
import com.jonaskv.ecommerce.auth_service.dto.request.CreateProfileRequest;
import com.jonaskv.ecommerce.auth_service.dto.request.LoginRequest;
import com.jonaskv.ecommerce.auth_service.dto.request.RegisterRequest;
import com.jonaskv.ecommerce.auth_service.dto.response.AuthResponse;
import com.jonaskv.ecommerce.auth_service.entity.Role;
import com.jonaskv.ecommerce.auth_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.jonaskv.ecommerce.auth_service.entity.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserServiceClient userServiceClient;

  public AuthResponse register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email already registered");
    }

    User user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.CUSTOMER)
        .build();

    User saved = userRepository.save(user);

    try {
      userServiceClient.createUserProfile(new CreateProfileRequest(saved.getId()));
    }
    catch (Exception e) {
      log.error("Couldnt create new profile for ID: " + saved.getId());
    }

    String token = jwtService.generateToken(saved);

    return AuthResponse.builder()
        .token(token)
        .userId(saved.getId())
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
  }

  public AuthResponse login(LoginRequest request) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

    user.setLastLogin(LocalDateTime.now());
    userRepository.save(user);

    String token = jwtService.generateToken(user);

    return AuthResponse.builder()
        .token(token)
        .userId(user.getId())
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
  }
} 
