package com.example.demo;

public class Article {
    private Long id;
    private String title;
    private String summary;
    private String contentUrl;
    private String imageUrl;

    public Article() {
    }

    public Article(Long id, String title, String summary, String contentUrl, String imageUrl) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.contentUrl = contentUrl;
        this.imageUrl = imageUrl;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    
    public String getContentUrl() { return contentUrl; }
    public void setContentUrl(String contentUrl) { this.contentUrl = contentUrl; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}