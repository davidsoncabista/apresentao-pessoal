# API de Portfólio Pessoal em Java

## 📜 Descrição

Esta é uma API RESTful desenvolvida em **Java com Spring Boot**, projetada para servir como o backend headless para o meu portfólio pessoal ([davidson.dev.br](https://davidson.dev.br)). A aplicação expõe endpoints que fornecem informações sobre meu perfil profissional, skills, projetos e outras informações relevantes.

A API foi totalmente containerizada com Docker e está implantada em uma infraestrutura on-premise que eu mesmo gerencio, utilizando Proxmox VE.

---

## ✨ Funcionalidades Principais

1.  **Desenvolvimento Backend:** Modelagem de dados e exposição de recursos via RESTful Web Services usando Spring Boot.
2.  **Containerização:** Definição de `Dockerfile` para ambientes de produção.
3.  **Frontend Simples:** Servir uma página de status estática (`index.html`) com Spring Boot.
4.  **Gerenciamento de Infraestrutura On-Premise:** Configuração e hospedagem de um serviço de armazenamento de objetos (MinIO) em um ambiente de virtualização próprio (Proxmox), demonstrando habilidades em provisionamento e gerenciamento de infraestrutura.
5.  **Integração com Armazenamento de Objetos:** Endpoint que consome de um bucket MinIO para listar dinamicamente URLs de imagens para uma galeria.

---

## 🛠️ Stack Tecnológico

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.2.1
* **Gerenciador de Dependências:** Maven
* **Containerização:** Docker
* **Banco de Dados (POC):** H2 Database (em memória)
* **Armazenamento de Objetos (S3-Compatible):** MinIO
* **Infraestrutura:** Proxmox VE com Ubuntu Server em container LXC

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

*   **`GET /profile`**: Retorna os dados do meu perfil profissional, incluindo nome, título, resumo e links para redes sociais.
*   **`GET /skills`**: Lista minhas competências técnicas, separadas por categoria (Infraestrutura Crítica, Desenvolvimento Full-Stack).
*   **`GET /projects`**: Apresenta uma lista dos meus principais projetos, com descrição, links e tecnologias utilizadas.
*   **`GET /health`**: Endpoint de verificação de saúde que retorna "OK" se a aplicação estiver no ar.
*   **`GET /api/gallery`**: Retorna uma lista de URLs de imagens armazenadas no bucket do MinIO, prontas para serem consumidas por um frontend de galeria.

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
