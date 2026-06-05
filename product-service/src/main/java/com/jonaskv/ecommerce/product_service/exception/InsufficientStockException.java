package com.jonaskv.ecommerce.product_service.exception;

public class InsufficientStockException extends RuntimeException{
  public InsufficientStockException(Long id) {
    super("Insufficient stock");
  }
}
