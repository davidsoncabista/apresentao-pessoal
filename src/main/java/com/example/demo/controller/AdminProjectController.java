package com.example.demo.controller;

import com.example.demo.Project;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.CacheEvict;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/projects")
public class AdminProjectController {

    private final ProjectRepository projectRepo;

    public AdminProjectController(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping
    public List<Project> list() {
        return projectRepo.findAll().stream().map(e -> new Project(e.getId(), e.getTitle(), e.getDescription(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getTechnologies(), e.getImageUrl())).collect(Collectors.toList());
    }

    @PostMapping
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Project> create(@Valid @RequestBody Project project) {
        ProjectEntity e = new ProjectEntity(project.getTitle(), project.getDescription(), project.getGithubUrl(), project.getDemoUrl(), project.getStatus(), project.getTechnologies(), project.getImageUrl());
        projectRepo.save(e);
        Project resp = new Project(e.getId(), e.getTitle(), e.getDescription(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getTechnologies(), e.getImageUrl());
        return ResponseEntity.created(URI.create("/admin/api/projects")).body(resp);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Project> update(@PathVariable Long id, @Valid @RequestBody Project project) {
        return projectRepo.findById(id).map(e -> {
            e.setTitle(project.getTitle());
            e.setDescription(project.getDescription());
            e.setGithubUrl(project.getGithubUrl());
            e.setDemoUrl(project.getDemoUrl());
            e.setStatus(project.getStatus());
            e.setTechnologies(project.getTechnologies());
            e.setImageUrl(project.getImageUrl());
            projectRepo.save(e);
            project.setId(id);
            return ResponseEntity.ok(project);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!projectRepo.existsById(id)) return ResponseEntity.notFound().build();
        projectRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
