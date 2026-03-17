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
        return projectRepo.findAll().stream().map(e -> new Project(e.getId(), e.getTitle(), e.getDescription(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getTechnologies(), e.getImageUrl())).collect(Collectors.toList());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Project> create(@RequestPart("project") @Valid Project project, @RequestPart(value = "file", required = false) MultipartFile file) {
        String imageUrl = project.getImageUrl();
        if (file != null && !file.isEmpty()) {
            imageUrl = imageService.uploadImage(file, "projects");
            System.out.println("Projeto criado - Arquivo recebido: " + file.getOriginalFilename() + ", URL gerada: " + imageUrl);
        } else {
            System.out.println("Projeto criado - Nenhum arquivo enviado, mantendo URL: " + imageUrl);
        }
        ProjectEntity e = new ProjectEntity(project.getTitle(), project.getDescription(), project.getGithubUrl(), project.getDemoUrl(), project.getStatus(), project.getTechnologies(), imageUrl);
        projectRepo.save(e);
        Project resp = new Project(e.getId(), e.getTitle(), e.getDescription(), e.getGithubUrl(), e.getDemoUrl(), e.getStatus(), e.getTechnologies(), e.getImageUrl());
        return ResponseEntity.created(URI.create("/admin/api/projects")).body(resp);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @CacheEvict(value = "projects", allEntries = true)
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestPart("project") @Valid Project project, @RequestPart(value = "file", required = false) MultipartFile file) {
return projectRepo.findById(id).map(e -> {
    if (file != null && !file.isEmpty()) {
        // Se já existia uma imagem antiga, apaga ela do MinIO primeiro
        if (e.getImageUrl() != null && !e.getImageUrl().isEmpty()) {
            imageService.deleteImage(e.getImageUrl());
        }
        
        // Sobe a nova imagem
        String imageUrl = imageService.uploadImage(file, "projects");
        e.setImageUrl(imageUrl);
        project.setImageUrl(imageUrl);
    } else {
        // Se não enviou arquivo novo, mantém a URL que veio no JSON
        e.setImageUrl(project.getImageUrl());
    }
            // Atualiza os dados do projeto
            e.setTitle(project.getTitle());
            e.setDescription(project.getDescription());
            e.setGithubUrl(project.getGithubUrl());
            e.setDemoUrl(project.getDemoUrl());
            e.setStatus(project.getStatus());
            e.setTechnologies(project.getTechnologies());
            e.setImageUrl(imageUrl);
            projectRepo.save(e);
            project.setId(id);
            project.setImageUrl(imageUrl);
            return ResponseEntity.ok(project);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

  @DeleteMapping("/{id}")
@CacheEvict(value = "projects", allEntries = true)
public ResponseEntity<Void> delete(@PathVariable Long id) {
    return projectRepo.findById(id).map(p -> {
        // Se o projeto tinha uma imagem, remove do MinIO
        if (p.getImageUrl() != null && !p.getImageUrl().isEmpty()) {
            imageService.deleteImage(p.getImageUrl());
        }
        
        projectRepo.deleteById(id);
        return ResponseEntity.noContent().<Void>build();
    }).orElse(ResponseEntity.notFound().build());
}
}
