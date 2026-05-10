package com.jonaskv.ecommerce.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jonaskv.ecommerce.user_service.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
