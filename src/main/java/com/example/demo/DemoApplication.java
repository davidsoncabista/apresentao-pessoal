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
            new Skill("Proxmox HA", 95, "Infraestrutura Crítica"),
            new Skill("Storage SAN", 90, "Infraestrutura Crítica"),
            new Skill("Terraform", 85, "Infraestrutura Crítica"),
            new Skill("Node.js / Express", 90, "Desenvolvimento Full-Stack"),
            new Skill("React / Next.js", 90, "Desenvolvimento Full-Stack"),
            new Skill("Python", 85, "Desenvolvimento Full-Stack"),
            new Skill("Roteamento/Switching (Cisco Nexus)", 85, "Telecomunicações")
        );
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return Arrays.asList(
            new Project("Gerenciador InfraCenter", 
                        "Plataforma O&M para Data Center, evoluída de PoC para cluster Proxmox HA/SAN/Cisco Nexus.",
                        "https://github.com/davidsoncabista/InfraCenter",
                        "#",
                        "Em Produção (Nuvem Privada)",
                        Arrays.asList("Proxmox", "Node.js", "React", "HP SAN", "Cisco Nexus")),
            new Project("Sistema de Gestão (Dungeon App)", 
                        "Plataforma full-stack para gestão de membros, reservas e pagamentos de associação.",
                        "https://github.com/davidsoncabista/Dungeon-App/",
                        "#",
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
