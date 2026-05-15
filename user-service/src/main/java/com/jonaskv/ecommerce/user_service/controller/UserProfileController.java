package com.jonaskv.ecommerce.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonaskv.ecommerce.user_service.dto.request.AddressRequest;
import com.jonaskv.ecommerce.user_service.dto.request.CreateProfileRequest;
import com.jonaskv.ecommerce.user_service.dto.request.UserProfileRequest;
import com.jonaskv.ecommerce.user_service.dto.response.AddressResponse;
import com.jonaskv.ecommerce.user_service.dto.response.UserProfileResponse;
import com.jonaskv.ecommerce.user_service.service.UserProfileService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping ("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService userProfileService;

  //Leeres Objekt erstellen
  @PostMapping
  public ResponseEntity<Void> createProfile(
      @RequestBody CreateProfileRequest request
  ) {
    userProfileService.createProfile(request.getUserId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  //UserProfil zurückgeben
  @GetMapping("/me/{userId}")
  public ResponseEntity<UserProfileResponse> getMyProfile(
      @RequestHeader("X-User-Id") Long userId
  ){
    return ResponseEntity.ok(userProfileService.getProfile(userId));
  }

  //UserProfil updaten
  @PutMapping ("/me")
  public ResponseEntity<UserProfileResponse> updateProfile(
      @RequestHeader("X-User-Id") Long userId,
      @RequestBody UserProfileRequest userProfileRequest
  ){
    return ResponseEntity.ok(userProfileService.updateProfile(userId, userProfileRequest));
  }
  
  //Alle Adressen abrufen
  @GetMapping ("/me/addresses")
  public ResponseEntity<List<AddressResponse>> getAddresses(
      @RequestHeader ("X-User-Id") Long userId
  ) {
    return ResponseEntity.ok(userProfileService.getAddresses(userId));
  }

  //Adresse hinzufügen
  @PostMapping("/addresses")
  public ResponseEntity<AddressResponse> addAddress(
      @RequestHeader("X-User-Id") Long userId,
      @RequestBody AddressRequest addressRequest
  ){
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(userProfileService.addAddress(addressRequest, userId));
  }

  @DeleteMapping("me/addresses/{addressId}")
  public ResponseEntity<Void> deleteAddress(
      @RequestHeader("X-User-Id") Long userId,
      @PathVariable Long addressId
  ) {
    userProfileService.deleteAddress(userId, addressId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("me/addresses/{addressId}/default")
  public ResponseEntity<AddressResponse> setDefaultAddress(
    @RequestHeader("X-User-Id") Long userId,
    @PathVariable Long addressId
  ){
    return ResponseEntity.ok(userProfileService.setDefaultAddress(userId, addressId));
  }

}
