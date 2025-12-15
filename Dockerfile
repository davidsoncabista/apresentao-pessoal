# Build Stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Run Stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 1. Instalar dependências (Adicionei dos2unix aqui para garantir)
RUN apt-get update && apt-get install -y curl dos2unix && rm -rf /var/lib/apt/lists/*

# 2. Baixar e instalar o cloudflared
RUN curl -L --output cloudflared.deb https://github.com/cloudflare/cloudflared/releases/latest/download/cloudflared-linux-amd64.deb && \
    dpkg -i cloudflared.deb && \
    rm cloudflared.deb

# 3. Copiar o JAR e o script
COPY --from=build /app/target/*.jar app.jar
COPY entrypoint.sh .

# 4. CONVERTER QUEBRAS DE LINHA E DAR PERMISSÃO (A Mágica acontece aqui)
RUN dos2unix entrypoint.sh && chmod +x entrypoint.sh

EXPOSE 8080

# 5. Start
ENTRYPOINT ["./entrypoint.sh"]