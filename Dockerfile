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

# Instalar apenas dos2unix para converter quebras de linha
RUN apt-get update && apt-get install -y dos2unix && rm -rf /var/lib/apt/lists/*

# Copiar o JAR e o script
COPY --from=build /app/target/*.jar app.jar
COPY entrypoint.sh .

# Converter quebras de linha e dar permissão
RUN dos2unix entrypoint.sh && chmod +x entrypoint.sh

EXPOSE 8080

# Start da aplicação
ENTRYPOINT ["./entrypoint.sh"]