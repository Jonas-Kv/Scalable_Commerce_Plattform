package com.jonaskv.ecommerce.user_service.dto.response;

import java.time.LocalDateTime;

import java.util.List;

import com.jonaskv.ecommerce.user_service.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserProfileResponse {
  
  public String firstName;

  public String lastName;

  public String phoneNumber;

  private String profilImageUrl;

  public LocalDateTime createdAt;

  public LocalDateTime updatedAt;

  public List <Address> addressesList;


}
