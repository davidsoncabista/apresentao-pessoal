package com.example.demo.controller;

import com.example.demo.Profile;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.CacheEvict;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/profile")
public class AdminProfileController {

    private final ProfileRepository profileRepo;

    public AdminProfileController(ProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile() {
        Optional<ProfileEntity> opt = profileRepo.findAll().stream().findFirst();
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        ProfileEntity e = opt.get();
        Profile p = new Profile(e.getId(), e.getName(), e.getTitle(), e.getSummary(), e.getLinkedinUrl(), e.getGithubUrl(), e.getYoutubeUrl());
        return ResponseEntity.ok(p);
    }

    @PostMapping
    @CacheEvict(value = "profile", allEntries = true)
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile profile) {
        ProfileEntity e = new ProfileEntity(profile.getName(), profile.getTitle(), profile.getSummary(), profile.getLinkedinUrl(), profile.getGithubUrl(), profile.getYoutubeUrl());
        profileRepo.save(e);
        Profile resp = new Profile(e.getId(), e.getName(), e.getTitle(), e.getSummary(), e.getLinkedinUrl(), e.getGithubUrl(), e.getYoutubeUrl());
        return ResponseEntity.created(URI.create("/admin/api/profile")).body(resp);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "profile", allEntries = true)
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @Valid @RequestBody Profile profile) {
        return profileRepo.findById(id).map(e -> {
            e.setName(profile.getName());
            e.setTitle(profile.getTitle());
            e.setSummary(profile.getSummary());
            e.setLinkedinUrl(profile.getLinkedinUrl());
            e.setGithubUrl(profile.getGithubUrl());
            e.setYoutubeUrl(profile.getYoutubeUrl());
            profileRepo.save(e);
            profile.setId(id);
            return ResponseEntity.ok(profile);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "profile", allEntries = true)
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        if (!profileRepo.existsById(id)) return ResponseEntity.notFound().build();
        profileRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
