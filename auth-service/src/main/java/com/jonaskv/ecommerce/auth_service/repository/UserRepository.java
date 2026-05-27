package com.jonaskv.ecommerce.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonaskv.ecommerce.auth_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
