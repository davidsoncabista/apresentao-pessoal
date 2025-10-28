package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@RestController
class ProfileController {

    @GetMapping("/profile")
    public Profile getProfile() {
        return new Profile("Davidson Conceição", "Arquiteto de Soluções de Infraestrutura / Desenvolvedor Full-Stack", "Apaixonado por construir sistemas sólidos e eficientes.");
    }

    @GetMapping("/skills")
    public List<Skill> getSkills() {
        return Arrays.asList(
            new Skill("Java", "Expert"),
            new Skill("Spring Boot", "Expert"),
            new Skill("Docker", "Proficient"),
            new Skill("Kubernetes", "Proficient"),
            new Skill("AWS", "Proficient"),
            new Skill("React", "Intermediate")
        );
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return Arrays.asList(
            new Project("InfraCenter Manager", "Plataforma para gerenciamento de infraestrutura de TI.")
        );
    }
}

class Profile {
    private String name;
    private String title;
    private String summary;

    public Profile(String name, String title, String summary) {
        this.name = name;
        this.title = title;
        this.summary = summary;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
}

class Skill {
    private String name;
    private String level;

    public Skill(String name, String level) {
        this.name = name;
        this.level = level;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }
}

class Project {
    private String name;
    private String description;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
