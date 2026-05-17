package com.jonaskv.ecommerce.user_service.exception;

public class AddressNotFoundException extends RuntimeException{
  public AddressNotFoundException(Long id) {
    super("Adresse nicht gefunden mit ID: " + id);
  }
}
