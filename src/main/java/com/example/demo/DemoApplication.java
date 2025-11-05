package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}

@RestController
class ProfileController {

    @GetMapping("/profile")
    public Profile getProfile() {
        return new Profile("Davidson Santos Conceição",
                           "Arquiteto de Soluções de Infraestrutura / Desenvolvedor Full-Stack",
                           "Minha filosofia é construir soluções robustas, não o próximo problema. Foco na união de Full-Stack e Infraestrutura Crítica.",
                           "https://www.linkedin.com/in/davidsonsconceicao/",
                           "https://github.com/davidsoncabista",
                           "https://www.youtube.com/@davidson.developer");
    }

    @GetMapping("/skills")
    public List<Skill> getSkills() {
        return Arrays.asList(
            new Skill("Docker", 80, "Infraestrutura Crítica", "https://cdn.simpleicons.org/docker/2496ED/FFFFFF"),
            new Skill("HTML5", 80, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/html5/E34F26/FFFFFF"),
            new Skill("Proxmox HA", 95, "Infraestrutura Crítica", "https://cdn.simpleicons.org/proxmox/E53B00/FFFFFF"),
            new Skill("Storage SAN", 80, "Infraestrutura Crítica", "https://img.icons8.com/ios-filled/50/FFFFFF/storage-area-network.png"),
            new Skill("Terraform", 70, "Infraestrutura Crítica", "https://cdn.simpleicons.org/terraform/7B42BC/FFFFFF"),
            new Skill("Node.js / Express", 90, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/nodedotjs/339933/FFFFFF"),
            new Skill("React / Next.js", 90, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/nextdotjs/000000/FFFFFF"),
            new Skill("Python", 60, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/python/3776AB/FFFFFF"),
            new Skill("Roteamento/Switching (Cisco Nexus)", 85, "Telecomunicações", "https://cdn.simpleicons.org/cisco/1BA0D7/FFFFFF"),
            new Skill("Java / Spring Boot", 60, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/springboot/6DB33F/FFFFFF")
        );
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return Arrays.asList(
            new Project("API de Portfólio em Java",
                        "API RESTful em Spring Boot que serve como backend headless para este portfólio.",
                        "https://github.com/davidsoncabista/apresentao-pessoal.git",
                        "https://davidson-portfolio-api.onrender.com/",
                        "Em Produção",
                        Arrays.asList("Java", "Spring Boot", "Maven", "Docker", "H2")),
            new Project("InfraVision",
                        "Plataforma O&M para Data Center, evoluída de PoC para cluster Proxmox HA/SAN/Cisco Nexus.",
                        "https://github.com/davidsoncabista/InfraCenter",
                        "https://studio--infravision2.us-central1.hosted.app/login1",
                        "Em Produção (Nuvem Privada)",
                        Arrays.asList("Proxmox", "Node.js", "React", "HP SAN", "Cisco Nexus")),
            new Project("Sistema de Gestão para Associação",
                        "Plataforma full-stack para gestão de membros, reservas e pagamentos de associação.",
                        "https://github.com/davidsoncabista/Dungeon-App/",
                        "https://studio--adbelm.us-central1.hosted.app/landing",
                        "Ativo / Lançado",
                        Arrays.asList("Next.js", "TypeScript", "Firebase", "Stripe"))
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
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getLinkedinUrl() { return linkedinUrl; }
    public String getGithubUrl() { return githubUrl; }
    public String getYoutubeUrl() { return youtubeUrl; }
}

class Skill {
    private String name;
    private Integer proficiency;
    private String category;
    private String logo;

    public Skill(String name, Integer proficiency, String category, String logo) {
        this.name = name;
        this.proficiency = proficiency;
        this.category = category;
        this.logo = logo;
    }

    // Getters
    public String getName() { return name; }
    public Integer getProficiency() { return proficiency; }
    public String getCategory() { return category; }
    public String getLogo() { return logo; }
}

class Project {
    private String title;
    private String description;
    private String githubUrl;
    private String demoUrl;
    private String status;
    private List<String> technologies;

    public Project(String title, String description, String githubUrl, String demoUrl, String status, List<String> technologies) {
        this.title = title;
        this.description = description;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.status = status;
        this.technologies = technologies;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGithubUrl() { return githubUrl; }
    public String getDemoUrl() { return demoUrl; }
    public String getStatus() { return status; }
    public List<String> getTechnologies() { return technologies; }
}
