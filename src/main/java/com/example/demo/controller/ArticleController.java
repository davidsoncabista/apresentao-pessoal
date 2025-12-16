package com.example.demo.controller;

import com.example.demo.Article; // Usa o DTO
import com.example.demo.service.ContentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ContentService contentService;

    public ArticleController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public List<Article> listPublic() {
        return contentService.getArticles();
    }
}