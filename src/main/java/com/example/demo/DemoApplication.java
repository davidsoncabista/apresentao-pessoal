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
        return new Profile("Davidson Conceição", 
                           "Arquiteto de Soluções de Infraestrutura / Desenvolvedor Full-Stack", 
                           "Apaixonado por construir sistemas sólidos e eficientes.",
                           "https://www.linkedin.com/in/davidson-conceicao/",
                           "https://github.com/davidsonconceicao",
                           "https://www.youtube.com/channel/UC-bML-0-T4-ST-I-3_D0_AQ");
    }

    @GetMapping("/skills")
    public List<Skill> getSkills() {
        return Arrays.asList(
            new Skill("Java", 100, "Backend"),
            new Skill("Spring Boot", 95, "Backend"),
            new Skill("Docker", 90, "DevOps"),
            new Skill("Kubernetes", 85, "DevOps"),
            new Skill("AWS", 80, "Cloud"),
            new Skill("React", 75, "Frontend")
        );
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return Arrays.asList(
            new Project("InfraCenter Manager", 
                        "Plataforma para gerenciamento de infraestrutura de TI.",
                        "https://github.com/davidsonconceicao/infracenter-manager",
                        "https://demo.infracenter.com",
                        "Em desenvolvimento",
                        Arrays.asList("Java", "Spring Boot", "React", "Docker"))
        );
    }
}

class Profile {
    private String name;
    private String title;
    private String summary;
    private String linkedinUrl;
    private String githubUrl;
    private String youtubeUrl;

    public Profile(String name, String title, String summary, String linkedinUrl, String githubUrl, String youtubeUrl) {
        this.name = name;
        this.title = title;
        this.summary = summary;
        this.linkedinUrl = linkedinUrl;
        this.githubUrl = githubUrl;
        this.youtubeUrl = youtubeUrl;
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

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
}

class Skill {
    private String name;
    private Integer proficiency;
    private String category;

    public Skill(String name, Integer proficiency, String category) {
        this.name = name;
        this.proficiency = proficiency;
        this.category = category;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Integer getProficiency() {
        return proficiency;
    }
    
    public String getCategory() {
        return category;
    }
}

class Project {
    private String name;
    private String description;
    private String githubUrl;
    private String demoUrl;
    private String status;
    private List<String> technologies;


    public Project(String name, String description, String githubUrl, String demoUrl, String status, List<String> technologies) {
        this.name = name;
        this.description = description;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.status = status;
        this.technologies = technologies;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public String getGithubUrl() {
        return githubUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getTechnologies() {
        return technologies;
    }
}