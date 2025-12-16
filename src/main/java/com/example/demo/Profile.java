package com.example.demo;

import jakarta.validation.constraints.NotBlank;

public class Profile {
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String title;

    private String summary;
    private String linkedinUrl;
    private String githubUrl;
    private String youtubeUrl;

    public Profile() {}

    public Profile(Long id, String name, String title, String summary, String linkedinUrl, String githubUrl, String youtubeUrl) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.summary = summary;
        this.linkedinUrl = linkedinUrl;
        this.githubUrl = githubUrl;
        this.youtubeUrl = youtubeUrl;
    }

    public Profile(String name, String title, String summary, String linkedinUrl, String githubUrl, String youtubeUrl) {
        this(null, name, title, summary, linkedinUrl, githubUrl, youtubeUrl);
    }

    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getLinkedinUrl() { return linkedinUrl; }
    public String getGithubUrl() { return githubUrl; }
    public String getYoutubeUrl() { return youtubeUrl; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
    public void setTitle(String title) { this.title = title; }
    public void setSummary(String summary) { this.summary = summary; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }
}
