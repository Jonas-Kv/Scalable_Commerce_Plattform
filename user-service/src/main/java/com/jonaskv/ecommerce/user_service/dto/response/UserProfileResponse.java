package com.jonaskv.ecommerce.user_service.dto.response;

import java.time.LocalDateTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
  
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String profilImageUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List <AddressResponse> addressesList;


}
