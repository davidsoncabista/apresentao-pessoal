package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class Project {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String githubUrl;
    private String demoUrl;

    private String status;
    private List<String> technologies;
    private String imageUrl;

    public Project() {}

    public Project(String title, String description, String githubUrl, String demoUrl, String status, List<String> technologies, String imageUrl) {
        this.title = title;
        this.description = description;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.status = status;
        this.technologies = technologies;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGithubUrl() { return githubUrl; }
    public String getDemoUrl() { return demoUrl; }
    public String getStatus() { return status; }
    public List<String> getTechnologies() { return technologies; }
    public String getImageUrl() { return imageUrl; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }
    public void setStatus(String status) { this.status = status; }
    public void setTechnologies(List<String> technologies) { this.technologies = technologies; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
