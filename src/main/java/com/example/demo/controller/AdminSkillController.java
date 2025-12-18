package com.example.demo.controller;

import com.example.demo.Skill;
import com.example.demo.entity.SkillEntity;
import com.example.demo.repository.SkillRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.CacheEvict;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/skills")
public class AdminSkillController {

    private final SkillRepository skillRepo;

    public AdminSkillController(SkillRepository skillRepo) {
        this.skillRepo = skillRepo;
    }

    @GetMapping
    public List<Skill> list() {
        return skillRepo.findAll().stream().map(e -> new Skill(e.getId(), e.getName(), e.getProficiency(), e.getCategory(), e.getLogo())).collect(Collectors.toList());
    }

    @PostMapping
    @CacheEvict(value = "skills", allEntries = true)
    public ResponseEntity<Skill> create(@Valid @RequestBody Skill skill) {
        SkillEntity e = new SkillEntity(skill.getName(), skill.getProficiency(), skill.getCategory(), skill.getLogo());
        skillRepo.save(e);
        Skill resp = new Skill(e.getId(), e.getName(), e.getProficiency(), e.getCategory(), e.getLogo());
        return ResponseEntity.created(URI.create("/admin/api/skills")).body(resp);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "skills", allEntries = true)
    public ResponseEntity<Skill> update(@PathVariable Long id, @Valid @RequestBody Skill skill) {
        return skillRepo.findById(id).map(e -> {
            e.setName(skill.getName());
            e.setProficiency(skill.getProficiency());
            e.setCategory(skill.getCategory());
            e.setLogo(skill.getLogo());
            skillRepo.save(e);
            skill.setId(id);
            return ResponseEntity.ok(skill);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "skills", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!skillRepo.existsById(id)) return ResponseEntity.notFound().build();
        skillRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
