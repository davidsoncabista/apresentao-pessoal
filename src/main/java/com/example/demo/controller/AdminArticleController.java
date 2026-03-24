package com.example.demo.controller;

import com.example.demo.Article;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ImageService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/articles")
public class AdminArticleController {

    private final ArticleRepository articleRepo;
    private final ImageService imageService;

    public AdminArticleController(ArticleRepository articleRepo, ImageService imageService) {
        this.articleRepo = articleRepo;
        this.imageService = imageService;
    }

    @GetMapping
    public List<Article> list() {
        return articleRepo.findAllByOrderByOrderIndexAsc().stream()
            .map(e -> {
                Article a = new Article(e.getId(), e.getTitle(), e.getSummary(), e.getContentUrl(), e.getImageUrl());
                a.setOrderIndex(e.getOrderIndex());
                return a;
            })
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @CacheEvict(value = "articles", allEntries = true)
    public ResponseEntity<Article> create(
            @RequestPart("article") Article article,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        String urlFinal = article.getImageUrl();
        if (file != null && !file.isEmpty()) {
            urlFinal = imageService.uploadImage(file, "articles");
        }

        ArticleEntity e = new ArticleEntity(article.getTitle(), article.getSummary(), article.getContentUrl(), urlFinal);
        
        if (article.getOrderIndex() != null) {
            e.setOrderIndex(article.getOrderIndex());
        }

        articleRepo.save(e);
        
        Article resp = new Article(e.getId(), e.getTitle(), e.getSummary(), e.getContentUrl(), e.getImageUrl());
        resp.setOrderIndex(e.getOrderIndex());
        return ResponseEntity.created(URI.create("/admin/api/articles")).body(resp);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @CacheEvict(value = "articles", allEntries = true)
    public ResponseEntity<Article> update(
            @PathVariable Long id, 
            @RequestPart("article") Article article,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        return articleRepo.findById(id).map(e -> {
            if (file != null && !file.isEmpty()) {
                if (e.getImageUrl() != null && !e.getImageUrl().isEmpty()) {
                    imageService.deleteImage(e.getImageUrl());
                }
                String novaUrl = imageService.uploadImage(file, "articles");
                e.setImageUrl(novaUrl);
            } else {
                e.setImageUrl(article.getImageUrl());
            }

            e.setTitle(article.getTitle());
            e.setSummary(article.getSummary());
            e.setContentUrl(article.getContentUrl());
            if (article.getOrderIndex() != null) {
                e.setOrderIndex(article.getOrderIndex());
            }
            
            articleRepo.save(e);
            article.setId(id);
            article.setOrderIndex(e.getOrderIndex());
            return ResponseEntity.ok(article);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "articles", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return articleRepo.findById(id).map(a -> {
            if (a.getImageUrl() != null && !a.getImageUrl().isEmpty()) {
                imageService.deleteImage(a.getImageUrl());
            }
            articleRepo.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // --- ENDPOINT PARA REORDENAR ---
    public static class OrderUpdate {
        public Long id;
        public Integer orderIndex;
    }

    @PutMapping("/reorder")
    @CacheEvict(value = "articles", allEntries = true)
    public ResponseEntity<Void> reorder(@RequestBody List<OrderUpdate> updates) {
        for (OrderUpdate update : updates) {
            articleRepo.findById(update.id).ifPresent(a -> {
                a.setOrderIndex(update.orderIndex);
                articleRepo.save(a);
            });
        }
        return ResponseEntity.ok().build();
    }
}