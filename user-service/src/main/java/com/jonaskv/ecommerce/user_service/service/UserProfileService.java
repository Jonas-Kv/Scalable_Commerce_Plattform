package com.jonaskv.ecommerce.user_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jonaskv.ecommerce.user_service.dto.response.AddressResponse;
import com.jonaskv.ecommerce.user_service.dto.response.UserProfileResponse;
import com.jonaskv.ecommerce.user_service.entity.UserProfile;
import com.jonaskv.ecommerce.user_service.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {
  
  private final UserProfileRepository userProfileRepository;

  public void createProfile(Long id) {
    UserProfile userProfile =  UserProfile.builder()
          .id(id)
          .build();
    userProfileRepository.save(userProfile);   
  }

  public UserProfileResponse getProfile (Long id) {

    UserProfile user= userProfileRepository.findById(id).orElseThrow(() -> new IllegalStateException(""));
    List <AddressResponse> addressResponseList= user.getAddresses()
          .stream()
          .map(address -> AddressResponse.builder()
          .id(address.getId())
          .street(address.getStreet())
          .city(address.getCity())
          .postalCode(address.getPostalCode())
          .country(address.getCountry())
          .isDefault(address.getIsDefault())
          .build()).toList();

    return UserProfileResponse.builder()
          .firstName(user.getFirstName())
          .lastName(user.getLastName())
          .phoneNumber(user.getPhoneNumber())
          .profilImageUrl(user.getProfilImageUrl())
          .addressesList(addressResponseList)
          .createdAt(user.getCreatedAt())
          .updatedAt(user.getUpdatedAt())
          .build();
    }
}
