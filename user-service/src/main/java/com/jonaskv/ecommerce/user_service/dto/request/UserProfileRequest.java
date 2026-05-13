package com.jonaskv.ecommerce.user_service.dto.request;

import java.time.LocalDateTime;

import com.jonaskv.ecommerce.user_service.entity.Address;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfileRequest {
  
  public String firstName;

  public String lastName;

  public String phoneNumber;

  private String profilImageUrl;

  public LocalDateTime createdAt;

  public LocalDateTime updatedAt;

  public List <Address> addressesList;
}
