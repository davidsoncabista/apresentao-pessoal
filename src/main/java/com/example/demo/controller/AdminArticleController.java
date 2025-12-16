package com.example.demo.controller;

import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/articles")
public class AdminArticleController {

    private final ArticleRepository repository;

    public AdminArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ArticleEntity> list() {
        return repository.findAll();
    }

    @PostMapping
    public ArticleEntity create(@RequestBody ArticleEntity article) {
        return repository.save(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleEntity> update(@PathVariable Long id, @RequestBody ArticleEntity novo) {
        return repository.findById(id)
            .map(artigo -> {
                artigo.setTitle(novo.getTitle());
                artigo.setSummary(novo.getSummary());
                artigo.setImageUrl(novo.getImageUrl());
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