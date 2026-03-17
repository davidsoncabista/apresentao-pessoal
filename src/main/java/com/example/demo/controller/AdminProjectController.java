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
        return projectRepo.findAll().stream()
            .map(e -> new Project(e.getId(), e.getTitle(), e.getDescription(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getTechnologies(), e.getImageUrl()))
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
            System.out.println("Novo projeto - Imagem upada: " + urlFinal);
        }

        ProjectEntity e = new ProjectEntity(project.getTitle(), project.getDescription(), project.getGithubUrl(), project.getDemoUrl(), project.getStatus(), project.getTechnologies(), urlFinal);
        projectRepo.save(e);
        
        Project resp = new Project(e.getId(), e.getTitle(), e.getDescription(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getTechnologies(), e.getImageUrl());
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
                // Remove imagem antiga se existir
                if (e.getImageUrl() != null && !e.getImageUrl().isEmpty()) {
                    imageService.deleteImage(e.getImageUrl());
                }
                // Sobe nova imagem
                String novaUrl = imageService.uploadImage(file, "projects");
                e.setImageUrl(novaUrl);
                System.out.println("Projeto atualizado - Nova imagem: " + novaUrl);
            } else {
                // Mantém a URL enviada no JSON (caso seja uma edição sem troca de foto)
                e.setImageUrl(project.getImageUrl());
            }

            e.setTitle(project.getTitle());
            e.setDescription(project.getDescription());
            e.setGithubUrl(project.getGithubUrl());
            e.setDemoUrl(project.getDemoUrl());
            e.setStatus(project.getStatus());
            e.setTechnologies(project.getTechnologies());
            
            projectRepo.save(e);
            project.setId(id);
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
}