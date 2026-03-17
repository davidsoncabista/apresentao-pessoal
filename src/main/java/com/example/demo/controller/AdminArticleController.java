package com.example.demo.controller;

import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/api/articles")
public class AdminArticleController {

    private final ArticleRepository repository;
    private final ImageService imageService;

    public AdminArticleController(ArticleRepository repository, ImageService imageService) {
        this.repository = repository;
        this.imageService = imageService;
    }

    @GetMapping
    public List<ArticleEntity> list() {
        return repository.findAll();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ArticleEntity create(
            @RequestPart("article") ArticleEntity article,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        if (file != null && !file.isEmpty()) {
            String imageUrl = imageService.uploadImage(file, "articles");
            article.setImageUrl(imageUrl);
        }
        return repository.save(article);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ArticleEntity> update(
            @PathVariable Long id, 
            @RequestPart("article") ArticleEntity novo,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        return repository.findById(id)
            .map(artigoExistente -> {
                if (file != null && !file.isEmpty()) {
                    // Limpeza: Se já existia uma imagem, remove do MinIO
                    if (artigoExistente.getImageUrl() != null && !artigoExistente.getImageUrl().isEmpty()) {
                        imageService.deleteImage(artigoExistente.getImageUrl());
                    }
                    // Sobe a nova
                    String imageUrl = imageService.uploadImage(file, "articles");
                    artigoExistente.setImageUrl(imageUrl);
                } else {
                    // Mantém a URL enviada se não houver novo ficheiro
                    artigoExistente.setImageUrl(novo.getImageUrl());
                }
                
                artigoExistente.setTitle(novo.getTitle());
                artigoExistente.setSummary(novo.getSummary());
                artigoExistente.setContentUrl(novo.getContentUrl());
                return ResponseEntity.ok(repository.save(artigoExistente));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id).map(artigo -> {
            // Limpeza: Remove a imagem do MinIO antes de apagar o registro
            if (artigo.getImageUrl() != null && !artigo.getImageUrl().isEmpty()) {
                imageService.deleteImage(artigo.getImageUrl());
            }
            repository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}