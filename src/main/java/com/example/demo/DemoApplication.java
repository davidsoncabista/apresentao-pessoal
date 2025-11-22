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
            // DevOps
            new Skill("Proxmox HA", 95, "DevOps", "https://cdn.simpleicons.org/proxmox/E53B00/FFFFFF"),
            new Skill("Terraform", 70, "DevOps", "https://cdn.simpleicons.org/terraform/7B42BC/FFFFFF"),
            new Skill("Kubernetes", 85, "DevOps", "https://cdn.simpleicons.org/kubernetes/326CE5/FFFFFF"),
            new Skill("Docker", 80, "DevOps", "https://cdn.simpleicons.org/docker/2496ED/FFFFFF"),

            // Infraestrutura Crítica
            new Skill("PUE", 85, "Infraestrutura Crítica", "https://cdn.simpleicons.org/leaf/4CAF50/FFFFFF"),
            new Skill("Sistemas de Clima", 90, "Infraestrutura Crítica", "https://cdn.simpleicons.org/snowflake/00BFFF/FFFFFF"),
            new Skill("UPS", 90, "Infraestrutura Crítica", "https://cdn.simpleicons.org/powershell/5391FE/FFFFFF"),
            new Skill("Sistemas de Energia", 95, "Infraestrutura Crítica", "https://cdn.simpleicons.org/lightning/FFD700/FFFFFF"),
            new Skill("USCA", 80, "Infraestrutura Crítica", "https://cdn.simpleicons.org/microchip/2D2D2D/FFFFFF"),
            new Skill("Comandos Elétricos", 85, "Infraestrutura Crítica", "https://cdn.simpleicons.org/cogs/808080/FFFFFF"),
            new Skill("Sistemas de Alarme", 90, "Infraestrutura Crítica", "https://cdn.simpleicons.org/sonarsource/E6001A/FFFFFF"),

            // Infraestrutura Física e Cabeamento
            new Skill("Cabeamento Estruturado", 95, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/ethernet-on.png"),
            new Skill("Fibra Óptica", 90, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/optical-fiber.png"),
            new Skill("Certificação de Redes", 85, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/network-checked.png"),
            new Skill("Montagem de Racks", 95, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/server.png"),

            // Desenvolvimento Full-Stack
            new Skill("HTML5", 80, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/html5/E34F26/FFFFFF"),
            new Skill("Node.js / Express", 90, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/nodedotjs/339933/FFFFFF"),
            new Skill("React / Next.js", 90, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/nextdotjs/000000/FFFFFF"),
            new Skill("Python", 60, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/python/3776AB/FFFFFF"),
            new Skill("Java", 60, "Desenvolvimento Full-Stack", "https://img.icons8.com/color/48/java-coffee-cup-logo.png")
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
                        Arrays.asList("Java", "Spring Boot", "Maven", "Docker", "H2"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605&psig=AOvVaw19nF_XG0kZ2h5R2Jz5qZ2P&ust=1700234084227000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCLiP6Z3hyYIDFQAAAAAdAAAAABAE"),
            new Project("Armazenamento de Objetos S3 (MinIO)",
                        "Instância autogerenciada de armazenamento de objetos compatível com a API S3, rodando em Proxmox. Usado para armazenar artefatos, backups e arquivos de mídia.",
                        "https://github.com/minio/minio",
                        "http://storage.davidson.dev.br",
                        "Em Produção (On-Premise)",
                        Arrays.asList("MinIO", "Proxmox", "Docker", "S3 API"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605&psig=AOvVaw19nF_XG0kZ2h5R2Jz5qZ2P&ust=1700234084227000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCLiP6Z3hyYIDFQAAAAAdAAAAABAE"),
            new Project("Nuvem Pessoal com Nextcloud",
                        "Plataforma de colaboração e armazenamento de arquivos, hospedada em infraestrutura própria (Proxmox) para garantir privacidade e controle total dos dados.",
                        "https://github.com/nextcloud/server.git",
                        "http://nextcloud.davidson.dev.br",
                        "Em Produção (On-Premise)",
                        Arrays.asList("Nextcloud", "Proxmox", "MariaDB", "Docker"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605&psig=AOvVaw19nF_XG0kZ2h5R2Jz5qZ2P&ust=1700234084227000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCLiP6Z3hyYIDFQAAAAAdAAAAABAE"),
            new Project("InfraVision",
                        "Plataforma O&M para Data Center, evoluída de PoC para cluster Proxmox HA/SAN/Cisco Nexus.",
                        "https://github.com/davidsoncabista/InfraCenter",
                        "https://studio--infravision2.us-central1.hosted.app/login1",
                        "Em Produção (Nuvem Privada)",
                        Arrays.asList("Proxmox", "Node.js", "React", "HP SAN", "Cisco Nexus"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605&psig=AOvVaw19nF_XG0kZ2h5R2Jz5qZ2P&ust=1700234084227000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCLiP6Z3hyYIDFQAAAAAdAAAAABAE"),
            new Project("Sistema de Gestão para Associação (SaaS)",
                        "Plataforma full-stack para gestão de membros, reservas e pagamentos de associação.",
                        "https://github.com/davidsoncabista/Dungeon-App/",
                        "https://studio--adbelm.us-central1.hosted.app/landing",
                        "Ativo / Lançado",
                        Arrays.asList("Next.js", "TypeScript", "Firebase", "Stripe"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605&psig=AOvVaw19nF_XG0kZ2h5R2Jz5qZ2P&ust=1700234084227000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCLiP6Z3hyYIDFQAAAAAdAAAAABAE")
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
    private String imageUrl;

    public Project(String title, String description, String githubUrl, String demoUrl, String status, List<String> technologies, String imageUrl) {
        this.title = title;
        this.description = description;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.status = status;
        this.technologies = technologies;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGithubUrl() { return githubUrl; }
    public String getDemoUrl() { return demoUrl; }
    public String getStatus() { return status; }
    public List<String> getTechnologies() { return technologies; }
    public String getImageUrl() { return imageUrl; }
}
