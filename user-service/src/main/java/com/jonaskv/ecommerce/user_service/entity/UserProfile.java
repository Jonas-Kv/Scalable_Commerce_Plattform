package com.jonaskv.ecommerce.user_service.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "user_profiles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {

  @Id //TODO: machen das Id von auth generiert wird
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String phoneNumber;

  private String profilImageUrl;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Address> addresses = new ArrayList<>();

  public void updateProfil(String firstName, String lastName, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
  }

  public void updateProfileImage(String profilImageUrl) {
    this.profilImageUrl = profilImageUrl;
  }

  public void updatePhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void addAddress(Address address) {
    addresses.add(address);
    address.assingToUser(this);
  }

  public void removeAddress(Address address) {
    addresses.remove(address);
    address.assingToUser(null);
  }

  public void setAddressToDefault(Long addressId) {
    addresses.forEach(a -> a.setIsDefault(a.getId().equals(addressId)));
  }

}