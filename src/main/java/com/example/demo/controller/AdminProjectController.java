package com.example.demo.controller;

import com.example.demo.Project;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.cache.annotation.CacheEvict;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/projects")
public class AdminProjectController {

    private final ProjectRepository projectRepo;
    private final ImageService imageService;

    public AdminProjectController(ProjectRepository projectRepo, ImageService imageService) {
        this.projectRepo = projectRepo;
        this.imageService = imageService;
    }

    @GetMapping
    public List<Project> list() {
        return projectRepo.findAllByOrderByOrderIndexAsc().stream()
            .map(e -> {
                Project p = new Project(e.getId(), e.getTitle(), e.getTitleEn(), e.getDescription(), e.getDescriptionEn(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getStatusEn(), e.getTechnologies(), e.getImageUrl());
                p.setOrderIndex(e.getOrderIndex());
                return p;
            })
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Project> create(
            @RequestPart("project") Project project,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        String urlFinal = project.getImageUrl();
        if (file != null && !file.isEmpty()) {
            urlFinal = imageService.uploadImage(file, "projects");
        }

        ProjectEntity e = new ProjectEntity(project.getTitle(), project.getTitleEn(), project.getDescription(), project.getDescriptionEn(), project.getGithubUrl(), project.getDemoUrl(), project.getStatus(), project.getStatusEn(), project.getTechnologies(), urlFinal);
        
        if (project.getOrderIndex() != null) {
            e.setOrderIndex(project.getOrderIndex());
        }

        projectRepo.save(e);
        
        Project resp = new Project(e.getId(), e.getTitle(), e.getTitleEn(), e.getDescription(), e.getDescriptionEn(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getStatusEn(), e.getTechnologies(), e.getImageUrl());
        resp.setOrderIndex(e.getOrderIndex());
        return ResponseEntity.created(URI.create("/admin/api/projects")).body(resp);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Project> update(
            @PathVariable Long id, 
            @RequestPart("project") Project project,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        return projectRepo.findById(id).map(e -> {
            if (file != null && !file.isEmpty()) {
                if (e.getImageUrl() != null && !e.getImageUrl().isEmpty()) {
                    imageService.deleteImage(e.getImageUrl());
                }
                String novaUrl = imageService.uploadImage(file, "projects");
                e.setImageUrl(novaUrl);
            } else {
                e.setImageUrl(project.getImageUrl());
            }

            e.setTitle(project.getTitle());
            e.setTitleEn(project.getTitleEn());
            e.setDescription(project.getDescription());
            e.setDescriptionEn(project.getDescriptionEn());
            e.setGithubUrl(project.getGithubUrl());
            e.setDemoUrl(project.getDemoUrl());
            e.setStatus(project.getStatus());
            e.setStatusEn(project.getStatusEn());
            e.setTechnologies(project.getTechnologies());
            if (project.getOrderIndex() != null) {
                e.setOrderIndex(project.getOrderIndex());
            }
            
            projectRepo.save(e);
            project.setId(id);
            project.setOrderIndex(e.getOrderIndex());
            return ResponseEntity.ok(project);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return projectRepo.findById(id).map(p -> {
            if (p.getImageUrl() != null && !p.getImageUrl().isEmpty()) {
                imageService.deleteImage(p.getImageUrl());
            }
            projectRepo.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public static class OrderUpdate {
        public Long id;
        public Integer orderIndex;
    }

    @PutMapping("/reorder")
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Void> reorder(@RequestBody List<OrderUpdate> updates) {
        for (OrderUpdate update : updates) {
            projectRepo.findById(update.id).ifPresent(p -> {
                p.setOrderIndex(update.orderIndex);
                projectRepo.save(p);
            });
        }
        return ResponseEntity.ok().build();
    }
}