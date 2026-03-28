package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    
    @NotBlank
    private String title;
    private String titleEn;

    @NotBlank
    private String description;
    private String descriptionEn;

    private String githubUrl;
    private String demoUrl;

    private String status;
    private String statusEn;
    private List<String> technologies;
    private String imageUrl;
    private Integer orderIndex = 0;

    public Project() {}

    public Project(Long id, String title, String titleEn, String description, String descriptionEn, String githubUrl, String demoUrl, String status, String statusEn, List<String> technologies, String imageUrl) {
        this.id = id;
        this.title = title;
        this.titleEn = titleEn;
        this.description = description;
        this.descriptionEn = descriptionEn;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.status = status;
        this.statusEn = statusEn;
        this.technologies = technologies;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String titleEn) { this.titleEn = titleEn; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDescriptionEn() { return descriptionEn; }
    public void setDescriptionEn(String descriptionEn) { this.descriptionEn = descriptionEn; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public String getDemoUrl() { return demoUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatusEn() { return statusEn; }
    public void setStatusEn(String statusEn) { this.statusEn = statusEn; }
    public List<String> getTechnologies() { return technologies; }
    public void setTechnologies(List<String> technologies) { this.technologies = technologies; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}