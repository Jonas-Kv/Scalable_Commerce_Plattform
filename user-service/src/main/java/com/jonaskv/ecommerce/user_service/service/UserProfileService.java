package com.jonaskv.ecommerce.user_service.service;

import java.util.List;

import com.jonaskv.ecommerce.user_service.dto.response.UserProfileResponse;
import com.jonaskv.ecommerce.user_service.entity.Address;
import com.jonaskv.ecommerce.user_service.entity.UserProfile;
import com.jonaskv.ecommerce.user_service.repository.AddressRepository;
import com.jonaskv.ecommerce.user_service.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserProfileService {
  
  private final UserProfileRepository userProfileRepository;
  private final AddressRepository addressRepository;

  public void createProfile(Long id) {
    UserProfile userProfile =  UserProfile.builder()
          .id(id)
          .build();
    userProfileRepository.save(userProfile);   
  }

  public UserProfileResponse getProfile (Long id) {
    UserProfile user= userProfileRepository.findById(id).orElseThrow(() -> new IllegalStateException(""));
    List <Address> address= addressRepository.findAllByUserId(id);
    UserProfileResponse userProfileResponse= UserProfileResponse.builder()
                                             .firstName(user.getFirstName())
                                             .lastName(user.getLastName())
                                             .phoneNumber(user.getPhoneNumber())
                                             .profilImageUrl(user.getProfilImageUrl())
                                             .addressesList(address)
                                             .createdAt(user.getCreatedAt())
                                             .updatedAt(user.getUpdatedAt())
                                             .build();
    return userProfileResponse;
  }
}
