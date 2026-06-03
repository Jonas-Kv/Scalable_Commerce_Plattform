package com.jonaskv.ecommerce.auth_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jonaskv.ecommerce.auth_service.dto.request.CreateProfileRequest;

@FeignClient(name = "user-service", url = "${services.user-service.url}")
public interface UserServiceClient {
  
  @PostMapping("/api/users")
  public void createUserProfile(@RequestBody CreateProfileRequest createProfileRequest);

}
