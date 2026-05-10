package com.jonaskv.ecommerce.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jonaskv.ecommerce.user_service.entity.Address;
import java.util.List;


public interface AddressRepository extends JpaRepository <Address, Long> {
  List<Address> findAllByUserId(Long userId);
}
