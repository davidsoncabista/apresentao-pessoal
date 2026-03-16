package com.example.demo.controller;

import com.example.demo.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    private final ImageService imageService;

    public GalleryController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<String> getGalleryImages() {
        return imageService.listImages();
    }

    @PostMapping("/upload")
    public String uploadGalleryImage(@RequestParam("file") MultipartFile file) {
        return imageService.uploadImage(file, "gallery");
    }
}
