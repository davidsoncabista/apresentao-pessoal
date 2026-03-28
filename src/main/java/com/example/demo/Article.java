package com.example.demo;

import java.io.Serializable;

public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String titleEn;
    private String summary;
    private String summaryEn;
    private String contentUrl;
    private String imageUrl;
    private Integer orderIndex = 0;

    public Article() {}

    public Article(Long id, String title, String titleEn, String summary, String summaryEn, String contentUrl, String imageUrl) {
        this.id = id;
        this.title = title;
        this.titleEn = titleEn;
        this.summary = summary;
        this.summaryEn = summaryEn;
        this.contentUrl = contentUrl;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String titleEn) { this.titleEn = titleEn; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getSummaryEn() { return summaryEn; }
    public void setSummaryEn(String summaryEn) { this.summaryEn = summaryEn; }
    public String getContentUrl() { return contentUrl; }
    public void setContentUrl(String contentUrl) { this.contentUrl = contentUrl; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}