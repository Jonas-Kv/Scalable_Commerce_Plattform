package com.jonaskv.ecommerce.user_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile, Long> {

  

  
}
