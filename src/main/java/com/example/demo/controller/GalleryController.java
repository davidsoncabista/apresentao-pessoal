package com.example.demo.controller;

import com.example.demo.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    private final ImageService imageService;

    public GalleryController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<String> getGalleryImages() {
        // AQUI ESTÁ O SEGREDO: Avisar para listar dentro da pasta "gallery"
        return imageService.listImages("gallery");
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadGalleryImages(@RequestParam("files") List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // AQUI SALVA NA PASTA "gallery"
                urls.add(imageService.uploadImage(file, "gallery"));
            }
        }
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGalleryImage(@RequestParam("url") String url) {
        imageService.deleteImage(url);
        return ResponseEntity.noContent().build();
    }
}