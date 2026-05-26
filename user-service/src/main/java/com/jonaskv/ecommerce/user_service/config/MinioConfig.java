package com.jonaskv.ecommerce.user_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

//Verbindung zur MiniO API
@Configuration
public class MinioConfig {
  
  @Value("${MINIO_ENDPOINT}")
  private String endpoint;

  @Value("${MINIO_ROOT_USER}")
    private String accessKey;

  @Value("${MINIO_ROOT_PASSWORD}")
  private String secretKey;

  @Bean
  public MinioClient minioClient() {
    return MinioClient.builder()
        .endpoint(endpoint)
        .credentials(accessKey, secretKey)
        .build();
  }
}
