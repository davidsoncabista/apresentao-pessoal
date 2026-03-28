package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    
    private String titleEn;
    private String summary;
    private String summaryEn;
    private String linkedinUrl;
    private String githubUrl;
    private String youtubeUrl;

    public Profile() {}

    public Profile(Long id, String name, String title, String titleEn, String summary, String summaryEn, String linkedinUrl, String githubUrl, String youtubeUrl) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.titleEn = titleEn;
        this.summary = summary;
        this.summaryEn = summaryEn;
        this.linkedinUrl = linkedinUrl;
        this.githubUrl = githubUrl;
        this.youtubeUrl = youtubeUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String titleEn) { this.titleEn = titleEn; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getSummaryEn() { return summaryEn; }
    public void setSummaryEn(String summaryEn) { this.summaryEn = summaryEn; }
    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public String getYoutubeUrl() { return youtubeUrl; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }
}