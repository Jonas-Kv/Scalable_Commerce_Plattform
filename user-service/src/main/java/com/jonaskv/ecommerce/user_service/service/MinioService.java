package com.jonaskv.ecommerce.user_service.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioService {
  
  private final MinioClient minioClient;

  @Value("${MINIO_BUCKET}")
  private String bucket;
  
  @Value("${MINIO_ENDPOINT}")
  private String endpoint;
  
  public String uploadImage(MultipartFile file) throws Exception{ 
    //bucket-check
    boolean exists = minioClient.bucketExists(BucketExistsArgs.builder()
        .bucket(bucket)
        .build()
    );

    if(!exists) {
      minioClient.makeBucket(MakeBucketArgs.builder()
          .bucket(bucket)
          .build()
      );
    }
    //UUID verhindert überschreibungen
    String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
    //upload
    minioClient.putObject(PutObjectArgs.builder()
        .bucket(bucket)
        .object(filename)
        .stream(file.getInputStream(), file.getSize(), -1)
        .contentType(file.getContentType())
        .build()
    );
    return endpoint + "/" + bucket + "/" + filename;
  }

  public void deleteImage(String imageUrl) throws Exception {
    String filename = imageUrl.substring(imageUrl.lastIndexOf("/")+1);

    minioClient.removeObject(RemoveObjectArgs.builder()
        .bucket(bucket)
        .object(filename)
        .build()
    );
  }
}
