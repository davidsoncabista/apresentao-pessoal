package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @Column(length = 1000)
    private String summary; // Pequena descrição
    
    private String imageUrl; // Capa do artigo
    private String contentUrl; // Link para o artigo (Medium, Dev.to, etc)

    public ArticleEntity() {}

    public ArticleEntity(String title, String summary, String imageUrl, String contentUrl) {
        this.title = title;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.contentUrl = contentUrl;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getContentUrl() { return contentUrl; }
    public void setContentUrl(String contentUrl) { this.contentUrl = contentUrl; }
}