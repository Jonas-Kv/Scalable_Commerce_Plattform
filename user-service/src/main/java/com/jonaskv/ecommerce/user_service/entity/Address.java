package com.jonaskv.ecommerce.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "addresses")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address{

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserProfile user;

  @Column (nullable = false)
  private String city;

  @Column (nullable = false)
  private String postalCode;

  @Column (nullable = false)
  private String street;

  @Column (nullable = false)
  @Builder.Default
  private String country = "Germamy";

  public void assingToUser( UserProfile user) {
    this.user = user;
  }



}