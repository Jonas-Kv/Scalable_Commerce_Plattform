package com.jonaskv.ecommerce.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonaskv.ecommerce.user_service.dto.request.CreateProfileRequest;
import com.jonaskv.ecommerce.user_service.dto.response.UserProfileResponse;
import com.jonaskv.ecommerce.user_service.service.UserProfileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping ("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService userProfileService;

  @PostMapping
  public ResponseEntity<Void> createProfile(@RequestBody CreateProfileRequest request) {
    userProfileService.createProfile(request.getUserId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/me/{userId}")
  public ResponseEntity<UserProfileResponse> getMyProfile(
    @PathVariable Long userId) {
    return ResponseEntity.ok(userProfileService.getProfile(userId));
  }

  
}
