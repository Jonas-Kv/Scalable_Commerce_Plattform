package com.jonaskv.ecommerce.product_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jonaskv.ecommerce.product_service.dto.request.ProductRequest;
import com.jonaskv.ecommerce.product_service.dto.response.ProductResponse;
import com.jonaskv.ecommerce.product_service.entity.Category;
import com.jonaskv.ecommerce.product_service.entity.Product;
import com.jonaskv.ecommerce.product_service.exception.CategoryNotFoundException;
import com.jonaskv.ecommerce.product_service.repository.CategoryRepository;
import com.jonaskv.ecommerce.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  
  private final ProductRepository productRepository;
  private final MinioService minioService;
  private final CategoryRepository categoryRepository;

  public ProductResponse createProduct(ProductRequest request, MultipartFile image) throws Exception{

    String imageUrl = minioService.uploadImage(image);
    Product product = Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .stock(request.getStock())
        .isAvailable(true)
        .imageUrl(imageUrl)
        .build();
    Category category = categoryRepository.findById(request.getCategoryId()).
        orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId()));
    category.addProduct(product);

    productRepository.save(product);
    
    return productResponseBuilder(product, category);
  }

  // public List<ProductResponse> getEveryProduct() {
  //   return productRepository.findAll().stream().map(p-> )
  // }



  private ProductResponse productResponseBuilder(Product product, Category category) {

    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .categoryId(category.getId())
        .isAvailable(product.getIsAvailable())
        .categoryName(category.getName())
        .imageUrlList(product.getProductImageList().stream().map(p-> p.getImageUrl()).toList())
        .build();
  }
}
