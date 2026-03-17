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
                    // Remove imagem antiga do MinIO se existir
                    if (artigoExistente.getImageUrl() != null && !artigoExistente.getImageUrl().isEmpty()) {
                        imageService.deleteImage(artigoExistente.getImageUrl());
                    }
                    // Sobe a nova imagem para a pasta 'articles'
                    String novaUrl = imageService.uploadImage(file, "articles");
                    artigoExistente.setImageUrl(novaUrl);
                    System.out.println("Artigo atualizado - Nova imagem: " + novaUrl);
                } else {
                    // Mantém a URL atual caso não tenha subido novo arquivo
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
            // Remove o arquivo do MinIO antes de deletar do banco
            if (artigo.getImageUrl() != null && !artigo.getImageUrl().isEmpty()) {
                imageService.deleteImage(artigo.getImageUrl());
            }
            repository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}