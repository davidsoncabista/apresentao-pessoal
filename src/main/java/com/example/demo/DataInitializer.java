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
            Profile defaultProfile = new Profile("Davidson Santos Conceição",
                    "Arquiteto de Soluções de Infraestrutura / Desenvolvedor Full-Stack",
                    "Minha filosofia é construir soluções robustas, não o próximo problema. Foco na união de Full-Stack e Infraestrutura Crítica.",
                    "https://www.linkedin.com/in/davidsonsconceicao/",
                    "https://github.com/davidsoncabista",
                    "https://www.youtube.com/@davidson.developer");

            profileRepo.save(new ProfileEntity(defaultProfile.getName(), defaultProfile.getTitle(), defaultProfile.getSummary(), defaultProfile.getLinkedinUrl(), defaultProfile.getGithubUrl(), defaultProfile.getYoutubeUrl()));
        }
        //==============================================================//
        //                      --- SKILLS ---           
        //==============================================================//
        if (skillRepo.count() == 0) {
            java.util.List<Skill> skills = Arrays.asList(
                    new Skill("Proxmox HA", 95, "DevOps", "https://cdn.simpleicons.org/proxmox/E53B00/FFFFFF"),
                    new Skill("Terraform", 70, "DevOps", "https://cdn.simpleicons.org/terraform/7B42BC/FFFFFF"),
                    new Skill("Kubernetes", 85, "DevOps", "https://cdn.simpleicons.org/kubernetes/326CE5/FFFFFF"),
                    new Skill("Docker", 80, "DevOps", "https://cdn.simpleicons.org/docker/2496ED/FFFFFF"),
                    new Skill("HTML5", 80, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/html5/E34F26/FFFFFF"),
                    new Skill("Node.js / Express", 90, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/nodedotjs/339933/FFFFFF"),
                    new Skill("React / Next.js", 90, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/nextdotjs/000000/FFFFFF"),
                    new Skill("Python", 60, "Desenvolvimento Full-Stack", "https://cdn.simpleicons.org/python/3776AB/FFFFFF"),
                    new Skill("Java", 60, "Desenvolvimento Full-Stack", "https://img.icons8.com/color/48/java-coffee-cup-logo.png"),
                    new Skill("PUE", 85, "Infraestrutura Crítica", "https://cdn.simpleicons.org/leaf/4CAF50/FFFFFF"),
                    new Skill("Sistemas de Clima", 90, "Infraestrutura Crítica", "https://cdn.simpleicons.org/snowflake/00BFFF/FFFFFF"),
                    new Skill("UPS", 90, "Infraestrutura Crítica", "https://cdn.simpleicons.org/powershell/5391FE/FFFFFF"),
                    new Skill("Sistemas de Energia", 95, "Infraestrutura Crítica", "https://cdn.simpleicons.org/lightning/FFD700/FFFFFF"),
                    new Skill("USCA", 80, "Infraestrutura Crítica", "https://cdn.simpleicons.org/microchip/2D2D2D/FFFFFF"),
                    new Skill("Comandos Elétricos", 85, "Infraestrutura Crítica", "https://cdn.simpleicons.org/cogs/808080/FFFFFF"),
                    new Skill("Sistemas de Alarme", 90, "Infraestrutura Crítica", "https://cdn.simpleicons.org/sonarsource/E6001A/FFFFFF"),
                    new Skill("Cabeamento Estruturado", 95, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/ethernet-on.png"),
                    new Skill("Fibra Óptica", 90, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/optical-fiber.png"),
                    new Skill("Certificação de Redes", 85, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/network-checked.png"),
                    new Skill("Montagem de Racks", 95, "Infraestrutura Física e Cabeamento", "https://img.icons8.com/ios-filled/50/server.png")
            );

            for (Skill s : skills) {
                skillRepo.save(new SkillEntity(s.getName(), s.getProficiency(), s.getCategory(), s.getLogo()));
            }
        }
        //==============================================================//
        //                      --- PROJECTS ---            
        //==============================================================//
        if (projectRepo.count() == 0) {
            java.util.List<Project> projects = Arrays.asList(
                    new Project("API de Portfólio em Java",
                            "API RESTful em Spring Boot que serve como backend headless para este portfólio.",
                            "https://github.com/davidsoncabista/apresentao-pessoal.git",
                            "https://davidson-portfolio-api.onrender.com/",
                            "Em Produção",
                            Arrays.asList("Java", "Spring Boot", "Maven", "Docker", "H2"),
                            "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                    new Project("Armazenamento de Objetos S3 (MinIO)",
                            "Instância autogerenciada de armazenamento de objetos compatível com a API S3, rodando em Proxmox. Usado para armazenar artefatos, backups e arquivos de mídia.",
                            "https://github.com/minio/minio",
                            "http://storage.davidson.dev.br",
                            "Em Produção (On-Premise)",
                            Arrays.asList("MinIO", "Proxmox", "Docker", "S3 API"),
                            "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                    new Project("Nuvem Pessoal com Nextcloud",
                            "Plataforma de colaboração e armazenamento de arquivos, hospedada em infraestrutura própria (Proxmox) para garantir privacidade e controle total dos dados.",
                            "https://github.com/nextcloud/server.git",
                            "http://nextcloud.davidson.dev.br",
                            "Em Produção (On-Premise)",
                            Arrays.asList("Nextcloud", "Proxmox", "MariaDB", "Docker"),
                            "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                    new Project("InfraVision",
                            "Plataforma O&M para Data Center, evoluída de PoC para cluster Proxmox HA/SAN/Cisco Nexus.",
                            "https://github.com/davidsoncabista/InfraCenter",
                            "https://studio--infravision2.us-central1.hosted.app/login1",
                            "Em Produção (Nuvem Privada)",
                            Arrays.asList("Proxmox", "Node.js", "React", "HP SAN", "Cisco Nexus"),
                            "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605"),
                    new Project("Sistema de Gestão para Associação (SaaS)",
                            "Plataforma full-stack para gestão de membros, reservas e pagamentos de associação.",
                            "https://github.com/davidsoncabista/Dungeon-App/",
                            "https://studio--adbelm.us-central1.hosted.app/landing",
                            "Ativo / Lançado",
                            Arrays.asList("Next.js", "TypeScript", "Firebase", "Stripe"),
                            "https://www.google.com/url?sa=i&url=https%3A%2F%2Ficon-icons.com%2Fpt%2Ficone%2Fdesenvolvedor-de-software-codificador-programa-programador-trabalhador%2F108605")
            );

            for (Project pr : projects) {
                projectRepo.save(new ProjectEntity(pr.getTitle(), pr.getDescription(), pr.getGithubUrl(), pr.getDemoUrl(), pr.getStatus(), pr.getTechnologies(), pr.getImageUrl()));
            }
        }
        //==============================================================//
        //              --- ARTIGOS (NOVO BLOCO) ---
        //==============================================================//
        if (articleRepo.count() == 0) {
            // Placeholder para imagem se não tiver
            String defaultImg = "https://images.unsplash.com/photo-1555066931-4365d14bab8c?auto=format&fit=crop&w=500&q=60";
            
            // Dados baseados no seu Frontend (Versão PT)
            java.util.List<ArticleEntity> articles = Arrays.asList(
                new ArticleEntity(
                    "Os Guardiões Silenciosos",
                    "Uma reflexão sobre os componentes e profissionais de infraestrutura que, silenciosamente, garantem a estabilidade e o funcionamento da tecnologia moderna.",
                    defaultImg, 
                    "https://www.linkedin.com/pulse/os-guardi%25C3%25B5es-silenciosos-davidson-s-concei%25C3%25A7%25C3%25A3o-nqdvf/"
                ),
                new ArticleEntity(
                    "Infraestrutura Estável: O Alicerce Secreto da IA Ágil",
                    "Uma análise sobre como uma infraestrutura de TI robusta e bem planejada é crucial para o sucesso e a agilidade de projetos de Inteligência Artificial.",
                    defaultImg,
                    "https://www.linkedin.com/pulse/infraestrutura-est%25C3%25A1vel-o-alicerce-secreto-da-%25C3%25A1gil-e-s-concei%25C3%25A7%25C3%25A3o-libdf"
                ),
                new ArticleEntity(
                    "Do Código ao Cabo: Uma Jornada de Redescoberta",
                    "Um artigo sobre a jornada para unir o desenvolvimento de software moderno à infraestrutura de hardware crítica, e por que essa união é essencial para o futuro da tecnologia.",
                    defaultImg,
                    "https://www.linkedin.com/pulse/do-c%25C3%25B3digo-ao-cabo-uma-jornada-de-redescoberta-da-davidson-s-concei%25C3%25A7%25C3%25A3o-yo1mf/"
                ),
                new ArticleEntity(
                    "Meu Primeiro Bug: Como uma 'Gambiarra' de R$50 me Ensinou Sobre Resolução de Problemas",
                    "Uma história sobre uma solução criativa do início da minha carreira e a lição que ela me ensinou sobre pensar fora da caixa para resolver problemas complexos.",
                    defaultImg,
                    "https://www.linkedin.com/pulse/meu-primeiro-bug-como-uma-gambiarra-de-r50-me-ensinou-s-concei%25C3%25A7%25C3%25A3o-3ewaf/"
                )
            );

            articleRepo.saveAll(articles);
        }
    }
}