package com.jonaskv.ecommerce.user_service.exception;

public class AddressNotFoundException extends RuntimeException{
  public AddressNotFoundException(Long id) {
    super("Adress not found with ID: " + id);
  }
}
