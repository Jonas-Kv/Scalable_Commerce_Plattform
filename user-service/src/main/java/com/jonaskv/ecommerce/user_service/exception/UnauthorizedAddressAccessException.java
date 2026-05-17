package com.jonaskv.ecommerce.user_service.exception;

public class UnauthorizedAddressAccessException extends RuntimeException{
  public UnauthorizedAddressAccessException() {
    super("Diese Adresse gehört nicht dir");
  }
}
