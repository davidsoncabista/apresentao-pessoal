package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.ContentService;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
@EnableCaching
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

    private final ContentService contentService;

    public ProfileController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/profile")
    public Profile getProfile() {
        Profile defaultProfile = new Profile(null, "Davidson Santos Conceição",
                "Arquiteto de Soluções de Infraestrutura / Desenvolvedor Full-Stack",
                "Infrastructure Solutions Architect / Full-Stack Developer",
                "Minha filosofia é construir soluções robustas, não o próximo problema. Foco na união de Full-Stack e Infraestrutura Crítica.",
                "My philosophy is to build robust solutions, not the next problem. Focus on uniting Full-Stack and Critical Infrastructure.",
                "https://www.linkedin.com/in/davidsonsconceicao/",
                "https://github.com/davidsoncabista",
                "https://www.youtube.com/@davidson.developer");

        return contentService.getProfileFallback(defaultProfile);
    }

    @GetMapping("/skills")
    public List<Skill> getSkills() {
        List<Skill> defaults = Arrays.asList(
                new Skill(null, "Proxmox HA", "Proxmox HA", 95, "DevOps", "DevOps", "https://cdn.simpleicons.org/proxmox/E53B00/FFFFFF"),
                new Skill(null, "Terraform", "Terraform", 70, "DevOps", "DevOps", "https://cdn.simpleicons.org/terraform/7B42BC/FFFFFF"),
                new Skill(null, "Kubernetes", "Kubernetes", 85, "DevOps", "DevOps", "https://cdn.simpleicons.org/kubernetes/326CE5/FFFFFF"),
                new Skill(null, "Docker", "Docker", 80, "DevOps", "DevOps", "https://cdn.simpleicons.org/docker/2496ED/FFFFFF"),
                new Skill(null, "HTML5", "HTML5", 80, "Desenvolvimento Full-Stack", "Full-Stack Development", "https://cdn.simpleicons.org/html5/E34F26/FFFFFF"),
                new Skill(null, "Node.js / Express", "Node.js / Express", 90, "Desenvolvimento Full-Stack", "Full-Stack Development", "https://cdn.simpleicons.org/nodedotjs/339933/FFFFFF"),
                new Skill(null, "React / Next.js", "React / Next.js", 90, "Desenvolvimento Full-Stack", "Full-Stack Development", "https://cdn.simpleicons.org/nextdotjs/000000/FFFFFF"),
                new Skill(null, "Python", "Python", 60, "Desenvolvimento Full-Stack", "Full-Stack Development", "https://cdn.simpleicons.org/python/3776AB/FFFFFF"),
                new Skill(null, "Java", "Java", 60, "Desenvolvimento Full-Stack", "Full-Stack Development", "https://img.icons8.com/color/48/java-coffee-cup-logo.png"),
                new Skill(null, "PUE", "PUE", 85, "Infraestrutura Crítica", "Critical Infrastructure", "https://cdn.simpleicons.org/leaf/4CAF50/FFFFFF"),
                new Skill(null, "Sistemas de Clima", "HVAC Systems", 90, "Infraestrutura Crítica", "Critical Infrastructure", "https://cdn.simpleicons.org/snowflake/00BFFF/FFFFFF"),
                new Skill(null, "UPS", "UPS", 90, "Infraestrutura Crítica", "Critical Infrastructure", "https://cdn.simpleicons.org/powershell/5391FE/FFFFFF"),
                new Skill(null, "Sistemas de Energia", "Power Systems", 95, "Infraestrutura Crítica", "Critical Infrastructure", "https://cdn.simpleicons.org/lightning/FFD700/FFFFFF"),
                new Skill(null, "USCA", "ATS", 80, "Infraestrutura Crítica", "Critical Infrastructure", "https://cdn.simpleicons.org/microchip/2D2D2D/FFFFFF"),
                new Skill(null, "Comandos Elétricos", "Electrical Controls", 85, "Infraestrutura Crítica", "Critical Infrastructure", "https://cdn.simpleicons.org/cogs/808080/FFFFFF"),
                new Skill(null, "Sistemas de Alarme", "Alarm Systems", 90, "Infraestrutura Crítica", "Critical Infrastructure", "https://cdn.simpleicons.org/sonarsource/E6001A/FFFFFF"),
                new Skill(null, "Cabeamento Estruturado", "Structured Cabling", 95, "Infraestrutura Física e Cabeamento", "Physical Infrastructure & Cabling", "https://img.icons8.com/ios-filled/50/ethernet-on.png"),
                new Skill(null, "Fibra Óptica", "Fiber Optics", 90, "Infraestrutura Física e Cabeamento", "Physical Infrastructure & Cabling", "https://img.icons8.com/ios-filled/50/optical-fiber.png"),
                new Skill(null, "Certificação de Redes", "Network Certification", 85, "Infraestrutura Física e Cabeamento", "Physical Infrastructure & Cabling", "https://img.icons8.com/ios-filled/50/network-checked.png"),
                new Skill(null, "Montagem de Racks", "Rack Assembly", 95, "Infraestrutura Física e Cabeamento", "Physical Infrastructure & Cabling", "https://img.icons8.com/ios-filled/50/server.png")
        );

        return contentService.getSkillsFallback(defaults);
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        List<Project> defaults = Arrays.asList(
                new Project(null, "API de Portfólio em Java",
                        "Java Portfolio API",
                        "API RESTful em Spring Boot que serve como backend headless para este portfólio.",
                        "RESTful API in Spring Boot serving as a headless backend for this portfolio.",
                        "https://github.com/davidsoncabista/apresentao-pessoal.git",
                        "https://davidson-portfolio-api.onrender.com/",
                        "Em Produção",
                        "In Production",
                        Arrays.asList("Java", "Spring Boot", "Maven", "Docker", "H2"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                new Project(null, "Armazenamento de Objetos S3 (MinIO)",
                        "S3 Object Storage (MinIO)",
                        "Instância autogerenciada de armazenamento de objetos compatível com a API S3, rodando em Proxmox. Usado para armazenar artefatos, backups e arquivos de mídia.",
                        "Self-managed object storage instance compatible with the S3 API, running on Proxmox. Used to store artifacts, backups, and media files.",
                        "https://github.com/minio/minio",
                        "http://storage.davidson.dev.br",
                        "Em Produção (On-Premise)",
                        "In Production (On-Premise)",
                        Arrays.asList("MinIO", "Proxmox", "Docker", "S3 API"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                new Project(null, "Nuvem Pessoal com Nextcloud",
                        "Personal Cloud with Nextcloud",
                        "Plataforma de colaboração e armazenamento de arquivos, hospedada em infraestrutura própria (Proxmox) para garantir privacidade e controle total dos dados.",
                        "Collaboration and file storage platform hosted on custom infrastructure (Proxmox) to ensure privacy and full control of data.",
                        "https://github.com/nextcloud/server.git",
                        "http://nextcloud.davidson.dev.br",
                        "Em Produção (On-Premise)",
                        "In Production (On-Premise)",
                        Arrays.asList("Nextcloud", "Proxmox", "MariaDB", "Docker"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                new Project(null, "InfraVision",
                        "InfraVision",
                        "Plataforma O&M para Data Center, evoluída de PoC para cluster Proxmox HA/SAN/Cisco Nexus.",
                        "O&M Platform for Data Center, evolved from PoC to Proxmox HA/SAN/Cisco Nexus cluster.",
                        "https://github.com/davidsoncabista/InfraCenter",
                        "https://studio--infravision2.us-central1.hosted.app/login1",
                        "Em Produção (Nuvem Privada)",
                        "In Production (Private Cloud)",
                        Arrays.asList("Proxmox", "Node.js", "React", "HP SAN", "Cisco Nexus"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                new Project(null, "Sistema de Gestão para Associação (SaaS)",
                        "Association Management System (SaaS)",
                        "Plataforma full-stack para gestão de membros, reservas e pagamentos de associação.",
                        "Full-stack platform for association member management, reservations, and payments.",
                        "https://github.com/davidsoncabista/Dungeon-App/",
                        "https://studio--adbelm.us-central1.hosted.app/landing",
                        "Ativo / Lançado",
                        "Active / Launched",
                        Arrays.asList("Next.js", "TypeScript", "Firebase", "Stripe"),
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605")
        );

        return contentService.getProjectsFallback(defaults);
    }
}