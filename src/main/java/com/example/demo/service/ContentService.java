package com.example.demo.service;

import com.example.demo.Article;
import com.example.demo.Profile;
import com.example.demo.Project;
import com.example.demo.Skill;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.entity.SkillEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ProfileRepository profileRepo;
    private final SkillRepository skillRepo;
    private final ProjectRepository projectRepo;
    private final ArticleRepository articleRepo;

    public ContentService(ProfileRepository profileRepo, SkillRepository skillRepo, ProjectRepository projectRepo, ArticleRepository articleRepo) {
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
        this.projectRepo = projectRepo;
        this.articleRepo = articleRepo;
    }

    
    @Cacheable(value = "profile")
    public Profile getProfileFallback(Profile defaultProfile) {
        List<ProfileEntity> list = profileRepo.findAll();
        if (list.isEmpty()) return defaultProfile;
        ProfileEntity e = list.get(0);
        return new Profile(e.getName(), e.getTitle(), e.getSummary(), e.getLinkedinUrl(), e.getGithubUrl(), e.getYoutubeUrl());
    }

    @Cacheable(value = "skills")
    public List<Skill> getSkillsFallback(List<Skill> defaultSkills) {
        List<SkillEntity> list = skillRepo.findAll();
        if (list.isEmpty()) return defaultSkills;
        return list.stream().map(e -> new Skill(e.getName(), e.getProficiency(), e.getCategory(), e.getLogo())).collect(Collectors.toList());
    }

    @Cacheable(value = "projects")
    public List<Project> getProjectsFallback(List<Project> defaultProjects) {
        List<ProjectEntity> list = projectRepo.findAll();
        if (list.isEmpty()) return defaultProjects;
        return list.stream().map(e -> new Project(e.getTitle(), e.getDescription(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getTechnologies(), e.getImageUrl())).collect(Collectors.toList());
    }

    @Cacheable(value = "articles")
    public List<Article> getArticles() {
        return articleRepo.findAll().stream()
            .map(e -> new Article(e.getId(), e.getTitle(), e.getSummary(), e.getContentUrl(), e.getImageUrl()))
            .collect(Collectors.toList());
    }
}
