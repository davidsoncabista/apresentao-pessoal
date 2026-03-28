package com.example.demo;

import com.example.demo.entity.ArticleEntity;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.entity.SkillEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.SkillRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class DataInitializer {

    private final ProfileRepository profileRepo;
    private final SkillRepository skillRepo;
    private final ProjectRepository projectRepo;
    private final ArticleRepository articleRepo;

    public DataInitializer(ProfileRepository profileRepo, SkillRepository skillRepo, ProjectRepository projectRepo, ArticleRepository articleRepo) {
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
        this.projectRepo = projectRepo;
        this.articleRepo = articleRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        // --- PERFIL ---
        if (profileRepo.count() == 0) {
            Profile defaultProfile = new Profile(null, "Davidson Santos Conceição",
                    "Arquiteto de Soluções de Infraestrutura / Desenvolvedor Full-Stack",
                    "Infrastructure Solutions Architect / Full-Stack Developer",
                    "Minha filosofia é construir soluções robustas, não o próximo problema. Foco na união de Full-Stack e Infraestrutura Crítica.",
                    "My philosophy is to build robust solutions, not the next problem. Focus on uniting Full-Stack and Critical Infrastructure.",
                    "https://www.linkedin.com/in/davidsonsconceicao/",
                    "https://github.com/davidsoncabista",
                    "https://www.youtube.com/@davidson.developer");

            profileRepo.save(new ProfileEntity(defaultProfile.getName(), defaultProfile.getTitle(), defaultProfile.getTitleEn(), defaultProfile.getSummary(), defaultProfile.getSummaryEn(), defaultProfile.getLinkedinUrl(), defaultProfile.getGithubUrl(), defaultProfile.getYoutubeUrl()));
        }

        // --- SKILLS ---
        if (skillRepo.count() == 0) {
            java.util.List<Skill> skills = Arrays.asList(
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

            for (Skill s : skills) {
                skillRepo.save(new SkillEntity(s.getName(), s.getNameEn(), s.getProficiency(), s.getCategory(), s.getCategoryEn(), s.getLogo()));
            }
        }

        // --- PROJECTS ---
        if (projectRepo.count() == 0) {
            java.util.List<Project> projects = Arrays.asList(
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

            for (Project pr : projects) {
                projectRepo.save(new ProjectEntity(pr.getTitle(), pr.getTitleEn(), pr.getDescription(), pr.getDescriptionEn(), pr.getGithubUrl(), pr.getDemoUrl(), pr.getStatus(), pr.getStatusEn(), pr.getTechnologies(), pr.getImageUrl()));
            }
        }

        // --- ARTIGOS ---
        if (articleRepo.count() == 0) {
            String defaultImg = "https://images.unsplash.com/photo-1555066931-4365d14bab8c?auto=format&fit=crop&w=500&q=60";

            java.util.List<ArticleEntity> articles = Arrays.asList(
                new ArticleEntity(
                    "Os Guardiões Silenciosos",
                    "The Silent Guardians",
                    "Uma reflexão sobre os componentes e profissionais de infraestrutura que, silenciosamente, garantem a estabilidade e o funcionamento da tecnologia moderna.",
                    "A reflection on the infrastructure components and professionals who silently ensure the stability and operation of modern technology.",
                    defaultImg,
                    "https://www.linkedin.com/pulse/os-guardi%25C3%25B5es-silenciosos-davidson-s-concei%25C3%25A7%25C3%25A3o-nqdvf/"
                ),
                new ArticleEntity(
                    "Infraestrutura Estável: O Alicerce Secreto da IA Ágil",
                    "Stable Infrastructure: The Secret Foundation of Agile AI",
                    "Uma análise sobre como uma infraestrutura de TI robusta e bem planejada é crucial para o sucesso e a agilidade de projetos de Inteligência Artificial.",
                    "An analysis of how a robust and well-planned IT infrastructure is crucial to the success and agility of Artificial Intelligence projects.",
                    defaultImg,
                    "https://www.linkedin.com/pulse/infraestrutura-est%25C3%25A1vel-o-alicerce-secreto-da-%25C3%25A1gil-e-s-concei%25C3%25A7%25C3%25A3o-libdf"
                ),
                new ArticleEntity(
                    "Do Código ao Cabo: Uma Jornada de Redescoberta",
                    "From Code to Cable: A Journey of Rediscovery",
                    "Um artigo sobre a jornada para unir o desenvolvimento de software moderno à infraestrutura de hardware crítica, e por que essa união é essencial para o futuro da tecnologia.",
                    "An article about the journey to unite modern software development with critical hardware infrastructure, and why this union is essential for the future of technology.",
                    defaultImg,
                    "https://www.linkedin.com/pulse/do-c%25C3%25B3digo-ao-cabo-uma-jornada-de-redescoberta-da-davidson-s-concei%25C3%25A7%25C3%25A3o-yo1mf/"
                ),
                new ArticleEntity(
                    "Meu Primeiro Bug: Como uma 'Gambiarra' de R$50 me Ensinou Sobre Resolução de Problemas",
                    "My First Bug: How a R$50 'Workaround' Taught Me About Problem Solving",
                    "Uma história sobre uma solução criativa do início da minha carreira e a lição que ela me ensinou sobre pensar fora da caixa para resolver problemas complexos.",
                    "A story about a creative solution from early in my career and the lesson it taught me about thinking outside the box to solve complex problems.",
                    defaultImg,
                    "https://www.linkedin.com/pulse/meu-primeiro-bug-como-uma-gambiarra-de-r50-me-ensinou-s-concei%25C3%25A7%25C3%25A3o-3ewaf/"
                )
            );

            articleRepo.saveAll(articles);
        }
    }
}