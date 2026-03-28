package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "articles")
public class ArticleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String titleEn;
    
    @Column(length = 1000)
    private String summary;
    
    @Column(length = 1000)
    private String summaryEn;
    
    private String imageUrl;
    private String contentUrl;

    @Column(name = "ordem")
    private Integer orderIndex = 0;

    public ArticleEntity() {}

    public ArticleEntity(String title, String titleEn, String summary, String summaryEn, String imageUrl, String contentUrl) {
        this.title = title;
        this.titleEn = titleEn;
        this.summary = summary;
        this.summaryEn = summaryEn;
        this.imageUrl = imageUrl;
        this.contentUrl = contentUrl;
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
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getContentUrl() { return contentUrl; }
    public void setContentUrl(String contentUrl) { this.contentUrl = contentUrl; }
    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}