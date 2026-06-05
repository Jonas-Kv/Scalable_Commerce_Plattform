package com.jonaskv.ecommerce.product_service.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jonaskv.ecommerce.product_service.dto.request.ProductRequest;
import com.jonaskv.ecommerce.product_service.dto.response.ProductResponse;
import com.jonaskv.ecommerce.product_service.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
  
  private final ProductService productService;

  //Create product, with Multipart picture
  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(
      @RequestPart("name") String name,
      @RequestPart("description") String description,
      @RequestPart("price") String price,
      @RequestPart("stock") String stock,
      @RequestPart("categoryId") String categoryId,
      @RequestPart("image") MultipartFile image
  ) throws Exception{
    ProductRequest request = ProductRequest.builder()
        .name(name)
        .description(description)
        .price(new BigDecimal(price))
        .stock(Long.parseLong(stock))
        .categoryId(Long.parseLong(categoryId))
        .build();

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.createProduct(request, image));
  }

  // @GetMapping
  // public ResponseEntity<List<ProductResponse>> getEveryProduct() {
  //   return  ResponseEntity.ok(productService.getEveryProduct());
  // }

  
}
