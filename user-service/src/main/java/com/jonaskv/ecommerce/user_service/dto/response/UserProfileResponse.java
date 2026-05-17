package com.jonaskv.ecommerce.user_service.dto.response;

import java.time.LocalDateTime;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {
  
  private Long id;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String profilImageUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List <AddressResponse> addressesList;


}
