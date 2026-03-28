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
    
    // CORREÇÃO AQUI: Adicionado key = "'default'"
    @Cacheable(value = "profile", key = "'default'")
    public Profile getProfileFallback(Profile defaultProfile) {
        List<ProfileEntity> list = profileRepo.findAll();
        if (list.isEmpty()) return defaultProfile;
        ProfileEntity e = list.get(0);
        return new Profile(e.getId(), e.getName(), e.getTitle(), e.getTitleEn(), e.getSummary(), e.getSummaryEn(), e.getLinkedinUrl(), e.getGithubUrl(), e.getYoutubeUrl());
    }

    // CORREÇÃO AQUI: Adicionado key = "'default'"
    @Cacheable(value = "skills", key = "'default'")
    public List<Skill> getSkillsFallback(List<Skill> defaultSkills) {
        List<SkillEntity> list = skillRepo.findAll();
        if (list.isEmpty()) return defaultSkills;
        return list.stream().map(e -> new Skill(e.getId(), e.getName(), e.getNameEn(), e.getProficiency(), e.getCategory(), e.getCategoryEn(), e.getLogo())).collect(Collectors.toList());
    }

    // CORREÇÃO AQUI: Adicionado key = "'default'"
    @Cacheable(value = "projects", key = "'default'")
    public List<Project> getProjectsFallback(List<Project> defaultProjects) {
        List<ProjectEntity> list = projectRepo.findAllByOrderByOrderIndexAsc();
        if (list.isEmpty()) return defaultProjects;
        
        return list.stream().map(e -> {
            Project p = new Project(e.getId(), e.getTitle(), e.getTitleEn(), e.getDescription(), e.getDescriptionEn(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getStatusEn(), e.getTechnologies(), e.getImageUrl());
            p.setOrderIndex(e.getOrderIndex());
            return p;
        }).collect(Collectors.toList());
    }

   // CORREÇÃO AQUI: Adicionado key = "'default'"
   @Cacheable(value = "articles", key = "'default'")
    public List<Article> getArticles() {
        return articleRepo.findAllByOrderByOrderIndexAsc().stream()
            .map(e -> {
                Article a = new Article(e.getId(), e.getTitle(), e.getTitleEn(), e.getSummary(), e.getSummaryEn(), e.getContentUrl(), e.getImageUrl());
                a.setOrderIndex(e.getOrderIndex());
                return a;
            })
            .collect(Collectors.toList());
    }
}