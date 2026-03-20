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

public List<String> listImages(String folder) {
        List<String> imageUrls = new ArrayList<>();
        try {
            // Se tiver uma pasta, adiciona a barra, senão busca na raiz
            String prefix = (folder != null && !folder.trim().isEmpty()) ? folder + "/" : "";

            Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .prefix(prefix) // <-- Agora o MinIO sabe em qual pasta procurar
                    .recursive(false) 
                    .build()
            );

            for (Result<Item> result : results) {
                Item item = result.get();
                
                // FILTRO: Só adiciona se NÃO for um diretório e NÃO terminar com "/"
                if (!item.isDir() && !item.objectName().endsWith("/")) {
                    String url = minioUrl + "/" + bucketName + "/" + item.objectName();
                    imageUrls.add(url);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error listing images from MinIO: " + e.getMessage());
        }
        return imageUrls;
    }
// Sobrecarga de método: lista os arquivos da raiz caso nenhuma pasta seja especificada
    public List<String> listImages() {
        return listImages(null);
    }
    

    public String uploadImage(MultipartFile file, String folder) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                originalFilename = "file";
            }

            String objectName;
            if (folder != null && !folder.trim().isEmpty()) {
                objectName = folder + "/" + System.currentTimeMillis() + "-" + originalFilename;
            } else {
                objectName = System.currentTimeMillis() + "-" + originalFilename;
            }

            InputStream inputStream = file.getInputStream();

            minioClient.putObject(
                io.minio.PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );

            return minioUrl + "/" + bucketName + "/" + objectName;

        } catch (Exception e) {
            throw new RuntimeException("Error uploading image to MinIO: " + e.getMessage());
        }
    }

    public void deleteImage(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return;
        }

        // Regra de segurança: só deletar se for do nosso servidor
        if (!imageUrl.startsWith(minioUrl)) {
            System.out.println("Tentativa de deletar imagem de servidor externo ignorada: " + imageUrl);
            return;
        }

        try {
            // Extrair o objectName da URL
            String objectName = imageUrl.replace(minioUrl + "/" + bucketName + "/", "");

            // Deletar do MinIO
            minioClient.removeObject(
                io.minio.RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build()
            );

            System.out.println("Imagem deletada com sucesso: " + objectName);

        } catch (Exception e) {
            System.out.println("Erro ao deletar imagem do MinIO: " + e.getMessage() + " (URL: " + imageUrl + ")");
        }
    }
}
