<p align="center">
  <img src="./Gemini_Generated_Image_jfcutvjfcutvjfcu.jpg" alt="Logo Davidson.dev: Letra D com um Bracket e um Servidor" width="200"/>
  <h1 align="center">Portfólio Backend API: Prova de Conceito de Habilidades</h1>
</p>

## 🚀 Visão Geral do Projeto

Este projeto é um serviço minimalista em Java, utilizando **Spring Boot 3**, projetado para atuar como o **Backend Headless** do meu portfólio (`davidson.dev.br`).

O objetivo é demonstrar a proficiência no desenvolvimento de APIs robustas e escaláveis, que é uma habilidade central para um **Arquiteto de Soluções de Infraestrutura**.

### 🎯 Proposta de Valor

O código serve como uma prova de conceito de como meus dados de portfólio (perfil, habilidades e projetos) são modelados e expostos através de endpoints REST, simulando a base de dados que alimentaria la aplicação web.

* **Lema:** "Construir soluções robustas, não o próximo problema."

---

## 🌐 Página de Status da API

Ao acessar a URL raiz da aplicação (ex: `http://localhost:8080/`), você encontrará uma página de status visual. Esta página serve como uma "porta de entrada" para a API, confirmando que o serviço está online e listando todos os endpoints disponíveis para interação.

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

## ⚙️ Endpoints da API

Esta API expõe os seguintes endpoints REST para consulta dos dados do meu perfil:

| Endpoint | Método | Descrição | Modelo de Resposta |
| :--- | :--- | :--- | :--- |
| `/profile` | `GET` | Retorna o nome, título, resumo e links sociais do autor. | `Profile` |
| `/skills` | `GET` | Retorna a lista de habilidades, incluindo categoria, nível de proficiência e logo. | `List<Skill>` |
| `/projects` | `GET` | Retorna a lista de projetos, incluindo título, descrição, status e tecnologias. | `List<Project>` |

---

## 💡 Habilidades Demonstradas

Este projeto demonstra as seguintes competências técnicas:

1.  **Desenvolvimento Backend:** Modelagem de dados e exposição de recursos via RESTful Web Services usando Spring Boot.
2.  **Containerização:** Definição de `Dockerfile` para ambientes de produção.
3.  **Frontend Simples:** Servir uma página de status estática (`index.html`) com Spring Boot.
4.  **Gerenciamento de Infraestrutura On-Premise:** Configuração e hospedagem de um serviço de armazenamento de objetos (MinIO) em um ambiente de virtualização próprio (Proxmox), demonstrando habilidades em provisionamento e gerenciamento de infraestrutura.

---

## 🚀 Como Executar o Projeto

Você pode rodar a aplicação localmente via Maven ou utilizando Docker.

### 1. Execução via Maven (Local)

Certifique-se de ter o JDK 17 e o Maven instalados.

```bash
# Instala as dependências e constrói o projeto
mvn clean install

# Executa a aplicação Spring Boot
mvn spring-boot:run
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
