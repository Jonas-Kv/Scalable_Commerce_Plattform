package com.jonaskv.ecommerce.user_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jonaskv.ecommerce.user_service.dto.request.AddressRequest;
import com.jonaskv.ecommerce.user_service.dto.request.UserProfileRequest;
import com.jonaskv.ecommerce.user_service.dto.response.AddressResponse;
import com.jonaskv.ecommerce.user_service.dto.response.UserProfileResponse;
import com.jonaskv.ecommerce.user_service.entity.Address;
import com.jonaskv.ecommerce.user_service.entity.UserProfile;
import com.jonaskv.ecommerce.user_service.repository.AddressRepository;
import com.jonaskv.ecommerce.user_service.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;


//TODO: Eigene Exceptions schreiben e.g UserNotFound
@Service
@RequiredArgsConstructor
public class UserProfileService {
  
  private final UserProfileRepository userProfileRepository;
  private final AddressRepository addressRepository;

  public void createProfile(Long userId) {
      UserProfile userProfile =  UserProfile.builder()
            .id(userId)
            .build();
      userProfileRepository.save(userProfile);   
  }

  public UserProfileResponse getProfile(Long userId) {
      UserProfile user= userProfileRepository.findById(userId).orElseThrow(() -> new IllegalStateException(""));
      return userProfileResponseBuilder(user);
  }

  public UserProfileResponse updateProfile(Long userId, UserProfileRequest request) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new IllegalStateException());
    user.updateProfil(
        request.getFirstName(),
        request.getLastName(),
        request.getPhoneNumber()
    );
    userProfileRepository.save(user);
    return userProfileResponseBuilder(user);
  }

  public List<AddressResponse> getAddresses(Long userId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new IllegalStateException());
    return getAddresses(user);
  }

  public AddressResponse addAddress(AddressRequest request, Long userId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new IllegalStateException());
    Address address = Address.builder()
        .city(request.getCity())
        .postalCode(request.getPostalCode())
        .street(request.getStreet())
        .isDefault(request.getIsDefault())
        .country(request.getCountry())
        .build();

    user.addAddress(address);

    userProfileRepository.save(user);

    return getAddressResponse(address);
  }

  public void deleteAddress(Long userId, Long addressId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new IllegalStateException());
    Address address  = addressRepository.findById(addressId).orElseThrow(() -> new IllegalStateException());
    user.removeAddress(address);
    userProfileRepository.save(user);
  }

  public AddressResponse setDefaultAddress(Long userId, Long addressId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new IllegalStateException());
    Address address  = addressRepository.findById(addressId).orElseThrow(() -> new IllegalStateException());
    user.setAddressToDefault(address);

    return getAddressResponse(address);
  }


  
  //returns a userProfileResponse object
  private UserProfileResponse userProfileResponseBuilder(UserProfile user) {
    return UserProfileResponse.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .phoneNumber(user.getPhoneNumber())
        .profilImageUrl(user.getProfilImageUrl())
        .addressesList(getAddresses(user))
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
  }

  //returns every address from a userProfile
  private List<AddressResponse> getAddresses (UserProfile user) {
    return user.getAddresses()
        .stream()
        .map(address -> AddressResponse.builder()
        .street(address.getStreet())
        .city(address.getCity())
        .postalCode(address.getPostalCode())
        .country(address.getCountry())
        .isDefault(address.getIsDefault())
        .build()).toList();
  }

  //returns an AddressResponseObject
  private AddressResponse getAddressResponse(Address address) {
    return AddressResponse.builder()
        .id(address.getId())
        .street(address.getStreet())
        .city(address.getCity())
        .postalCode(address.getPostalCode())
        .country(address.getCountry())
        .isDefault(address.getIsDefault())
        .build();
  }
}
