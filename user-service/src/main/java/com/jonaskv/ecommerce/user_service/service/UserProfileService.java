package com.jonaskv.ecommerce.user_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jonaskv.ecommerce.user_service.dto.request.AddressRequest;
import com.jonaskv.ecommerce.user_service.dto.request.UserProfileRequest;
import com.jonaskv.ecommerce.user_service.dto.response.AddressResponse;
import com.jonaskv.ecommerce.user_service.dto.response.UserProfileResponse;
import com.jonaskv.ecommerce.user_service.dto.response.UserSummary;
import com.jonaskv.ecommerce.user_service.entity.Address;
import com.jonaskv.ecommerce.user_service.entity.UserProfile;
import com.jonaskv.ecommerce.user_service.exception.AddressNotFoundException;
import com.jonaskv.ecommerce.user_service.exception.UnauthorizedAddressAccessException;
import com.jonaskv.ecommerce.user_service.exception.UserNotFoundException;
import com.jonaskv.ecommerce.user_service.repository.AddressRepository;
import com.jonaskv.ecommerce.user_service.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {
  
  private final UserProfileRepository userProfileRepository;
  private final AddressRepository addressRepository;

  public void createProfile(Long userId) {
    if(userProfileRepository.existsById(userId)) return;
    UserProfile userProfile =  UserProfile.builder()
        .id(userId)
        .build();
    userProfileRepository.save(userProfile);
  }

  public UserProfileResponse getProfile(Long userId) {
    UserProfile user= userProfileRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    return userProfileResponseBuilder(user);
  }

  public UserProfileResponse updateProfile(Long userId, UserProfileRequest request) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    user.updateProfil(
        request.getFirstName(),
        request.getLastName(),
        request.getPhoneNumber()
    );
    userProfileRepository.save(user);
    return userProfileResponseBuilder(user);
  }

  public List<AddressResponse> getAddresses(Long userId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    return mapAddressesToResponse(user);
  }

  public AddressResponse addAddress(AddressRequest request, Long userId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    if(Boolean.TRUE.equals(request.getIsDefault())) {
      user.getAddresses().forEach(a -> a.setIsDefault(false));
    }
    Address address = Address.builder()
        .city(request.getCity())
        .postalCode(request.getPostalCode())
        .street(request.getStreet())
        .isDefault(request.getIsDefault())
        .country(request.getCountry())
        .build();

    user.addAddress(address);
    UserProfile savedUser = userProfileRepository.save(user);
    Address savedAddress = savedUser.getAddresses().get(savedUser.getAddresses().size()-1);

    return getAddressResponse(savedAddress);
  }

  public void deleteAddress(Long userId, Long addressId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    Address address  = addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException(addressId));
    if(!address.getUser().getId().equals(userId)) {
      throw new UnauthorizedAddressAccessException();
    }
    user.removeAddress(address);
    userProfileRepository.save(user);
  }

  //TODO morgen nochmal anschauen
  public void setDefaultAddress(Long userId, Long addressId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new IllegalStateException());
    if(!addressRepository.existsById(addressId)) {
      throw new AddressNotFoundException(addressId);
    }
    user.setAddressToDefault(addressId);
    userProfileRepository.save(user);
  }

  public UserSummary getUserById(Long userId) {
    UserProfile user = userProfileRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    return UserSummary.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .build();
  }
  
  //returns a userProfileResponse object
  private UserProfileResponse userProfileResponseBuilder(UserProfile user) {
    return UserProfileResponse.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .phoneNumber(user.getPhoneNumber())
        .profilImageUrl(user.getProfilImageUrl())
        .addressesList(mapAddressesToResponse(user))
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
  }

  //returns every address from a userProfile
  private List<AddressResponse> mapAddressesToResponse(UserProfile user) {
    return user.getAddresses()
        .stream()
        .map(address -> AddressResponse.builder()
        .id(address.getId())
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
