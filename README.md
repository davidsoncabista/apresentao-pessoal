# API de Portfólio Pessoal em Java

## 📜 Descrição

Esta é uma API RESTful desenvolvida em **Java com Spring Boot**, projetada para servir como o backend headless para o meu portfólio pessoal ([davidson.dev.br](https://davidson.dev.br)). A aplicação expõe endpoints que fornecem informações sobre meu perfil profissional, skills, projetos e outras informações relevantes.

A API foi totalmente containerizada com Docker e está implantada em uma infraestrutura on-premise que eu mesmo gerencio, utilizando Proxmox VE.

---

## ✨ Funcionalidades Principais

1.  **Desenvolvimento Backend:** Modelagem de dados e exposição de recursos via RESTful Web Services usando Spring Boot.
2.  **Containerização:** Definição de `Dockerfile` para ambientes de produção.
3.  **Gerenciamento de Infraestrutura On-Premise:** Configuração e hospedagem de serviços em um ambiente de virtualização próprio (Proxmox), demonstrando habilidades em provisionamento e gerenciamento.
4.  **📸 Galeria e Mídia:** Integração com Object Storage (MinIO) para servir ativos de mídia dinamicamente.
5.  **Frontend Simples:** Servir uma página de status estática (`index.html`) com Spring Boot.

---

## 🛠️ Stack Tecnológico

*   **Linguagem:** Java 17
*   **Framework:** Spring Boot 3.2.1
*   **Gerenciador de Dependências:** Maven
*   **Containerização:** Docker
*   **Armazenamento de Objetos (S3-Compatible):** MinIO
*   **Infraestrutura:** Proxmox VE com Ubuntu Server em container LXC

---

## 🧠 Competências

A API organiza as competências nas seguintes categorias, que podem ser consultadas através do endpoint `/skills`:

### DevOps
- Provisionamento e automação de infraestrutura.
- Orquestração de containers (Docker).
- Práticas de Integração e Entrega Contínua (CI/CD).

### Infraestrutura Crítica
- **PUE (Power Usage Effectiveness):** Análise e otimização da eficiência energética de Data Centers.
- **Sistemas de Clima (HVAC):** Gerenciamento e monitoramento de sistemas de refrigeração de precisão.
- **UPS (Uninterruptible Power Supply):** Implantação e manutenção de sistemas de energia ininterrupta.
- **Sistemas de Energia:** Gerenciamento de fontes e retificadores de energia (ex: Eltek, Delta).
- **USCA (Unidade de Supervisão para Corrente Alternada):** Monitoramento e controle de quadros de distribuição.
- **Comandos Elétricos:** Automação e controle de acionamentos em infraestrutura.
- **Sistemas de Alarme:** Configuração de monitoramento por contato seco para eventos críticos.

### Desenvolvimento Full-Stack
- Desenvolvimento de APIs RESTful com Java e Spring Boot.
- Modelagem e consumo de serviços web.

---

## 🏗️ Arquitetura de Hospedagem On-Premise

A aplicação está hospedada em uma infraestrutura própria, gerenciada com Proxmox VE. Cada serviço é isolado em seu próprio container LXC para garantir modularidade e segurança. O acesso externo é gerenciado pelo Cloudflare, que aponta para os serviços através dos seguintes subdomínios:

- **`nextcloud.davidson.dev.br`**: Instância do Nextcloud para armazenamento e colaboração.
- **`s3.davidson.dev.br:9000`**: Endpoint do MinIO, compatível com a API S3 da AWS.
- **`storage.davidson.dev.br:9090`**: Interface de usuário do MinIO.
- **`davidson.dev.br`**: Página principal (frontend), servida por um container Nginx na porta 80.

O Nextcloud utiliza uma instância dedicada do **MariaDB** como banco de dados.

---

## ⚙️ Endpoints da API

Esta API expõe os seguintes endpoints REST para consulta dos dados do meu perfil:

*   **`GET /profile`**: Retorna os dados do meu perfil profissional.
*   **`GET /skills`**: Lista minhas competências técnicas, agrupadas por categoria.
*   **`GET /projects`**: Apresenta uma lista dos meus principais projetos.
*   **`GET /health`**: Endpoint de verificação de saúde da aplicação.
*   **`GET /api/gallery`**:
    *   **Descrição:** Lista as URLs públicas de todas as imagens armazenadas no bucket `portfolio-image`.
    *   **Resposta:** JSON Array de Strings `["https://s3.../img1.jpg", "https://s3.../img2.jpg"]`.
    *   **Uso:** Consumido pelo Frontend para renderizar a seção de fotos/certificados.

---

## 🚀 Como Executar o Projeto

Você pode rodar a aplicação localmente via Maven Wrapper ou utilizando Docker.

### 1. Execução via Maven Wrapper (Local)

Certifique-se de ter o JDK 17 instalado.

```bash
# (No Linux/macOS) Torna o script do wrapper executável
chmod +x mvnw

# Instala as dependências e constrói o projeto
./mvnw clean install

# Executa a aplicação Spring Boot
./mvnw spring-boot:run

#Execute para ambiente dev
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
A página de status da API estará disponível em `http://localhost:8080/`. Os endpoints da API podem ser acessados a partir dessa base (ex: `http://localhost:8080/profile`).

### 2. Execução via Docker (Containerização)
Use o Docker para construir a imagem e iniciar o container:

```bash
# Constrói a imagem Docker a partir do Dockerfile
docker build -t davidson-portfolio-api .

# Executa o container
docker run -p 8080:8080 davidson-portfolio-api
```
A página de status estará disponível em `http://localhost:8080/`, com os endpoints da API nos seus respectivos caminhos.

## 📬 Contato

Conecte-se comigo e explore minha experiência em Arquitetura de Soluções e Infraestrutura Crítica:

**LinkedIn:** https://www.linkedin.com/in/davidsonsconceicao/

**GitHub:** https://github.com/davidsoncabista

**Portfólio:** https://davidson.dev.br
