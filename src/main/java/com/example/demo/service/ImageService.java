package com.example.demo.service;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.url}")
    private String minioUrl;

    public ImageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public List<String> listImages() {
        List<String> imageUrls = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).build()
            );

            for (Result<Item> result : results) {
                Item item = result.get();
                String url = minioUrl + "/" + bucketName + "/" + item.objectName();
                imageUrls.add(url);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error listing images from MinIO: " + e.getMessage());
        }
        return imageUrls;
    }

    public String uploadImage(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();

            minioClient.putObject(
                io.minio.PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );

            return minioUrl + "/" + bucketName + "/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Error uploading image to MinIO: " + e.getMessage());
        }
    }
}
