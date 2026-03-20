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
        return imageService.listImages();
    }

    // Atualizado: Agora recebe múltiplos arquivos (List<MultipartFile>)
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadGalleryImages(@RequestParam("files") List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                urls.add(imageService.uploadImage(file, "gallery"));
            }
        }
        return ResponseEntity.ok(urls);
    }

    // Novo: Rota para deletar imagem recebendo a URL
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGalleryImage(@RequestParam("url") String url) {
        imageService.deleteImage(url);
        return ResponseEntity.noContent().build();
    }
}