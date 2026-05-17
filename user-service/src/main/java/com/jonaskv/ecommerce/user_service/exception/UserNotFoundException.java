package com.jonaskv.ecommerce.user_service.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(Long id) {
    super("User nicht gefunden mit ID: " + id);
  }
}
