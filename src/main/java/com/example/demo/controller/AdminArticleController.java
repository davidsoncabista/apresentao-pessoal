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
    public ArticleEntity create(@RequestPart("article") ArticleEntity article, @RequestPart(value = "file", required = false) MultipartFile file) {
        String imageUrl = article.getImageUrl();
        if (file != null && !file.isEmpty()) {
            imageUrl = imageService.uploadImage(file, "articles");
            System.out.println("Artigo criado - Arquivo recebido: " + file.getOriginalFilename() + ", URL gerada: " + imageUrl);
        } else {
            System.out.println("Artigo criado - Nenhum arquivo enviado, mantendo URL: " + imageUrl);
        }
        article.setImageUrl(imageUrl);
        return repository.save(article);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ArticleEntity> update(@PathVariable Long id, @RequestPart("article") ArticleEntity novo, @RequestPart(value = "file", required = false) MultipartFile file) {
        return repository.findById(id)
            .map(artigo -> {
                String imageUrl = novo.getImageUrl();
                if (file != null && !file.isEmpty()) {
                    imageUrl = imageService.uploadImage(file, "articles");
                    System.out.println("Artigo atualizado - Arquivo recebido: " + file.getOriginalFilename() + ", URL gerada: " + imageUrl);
                } else {
                    System.out.println("Artigo atualizado - Nenhum arquivo enviado, mantendo URL: " + imageUrl);
                }
                artigo.setTitle(novo.getTitle());
                artigo.setSummary(novo.getSummary());
                artigo.setImageUrl(imageUrl);
                artigo.setContentUrl(novo.getContentUrl());
                return ResponseEntity.ok(repository.save(artigo));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}