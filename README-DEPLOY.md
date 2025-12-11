# Deploy to Render — Quick Instructions

This file contains the minimal steps and environment variables to deploy the
`apresentao-pessoal` Spring Boot app to Render.

## Build & Start (Render)
- Build command:
```
./mvnw -DskipTests package
```
- Start command:
```
java -jar target/*.jar
```

## Local development (no remote DB)
To run locally without access to the remote MariaDB (useful when developing on your laptop), start with the `dev` profile which uses H2 in-memory DB:

```
./mvnw -Dspring-boot.run.profiles=dev spring-boot:run
```

Or run the jar with:

```
java -jar -Dspring.profiles.active=dev target/*.jar
```

This avoids the MariaDB connection timeout shown when the remote DB is not reachable.

## Environment variables (copy/paste into Render -> Environment)
Mark the following variables as **Secret** in Render where noted.

- `SPRING_DATASOURCE_URL` (or set `DB_HOST` + `DB_NAME`) — JDBC URL to MariaDB
  - Example: `jdbc:mariadb://mariadb.davidson.dev.br/portfolio`
- `SPRING_DATASOURCE_USERNAME` — DB user (Secret)
- `SPRING_DATASOURCE_PASSWORD` — DB password (Secret)
- `DB_HOST` — alternative: `mariadb.davidson.dev.br`
- `DB_NAME` — alternative: `portfolio`

- `MINIO_URL` — MinIO endpoint (not secret)
- `MINIO_ACCESS_KEY` — MinIO access key (Secret)
- `MINIO_SECRET_KEY` — MinIO secret key (Secret)
- `MINIO_BUCKET_NAME` — bucket name (not secret)

- `APP_ADMIN_USERNAME` — admin user (Secret)
- `APP_ADMIN_PASSWORD` — admin password (Secret)

- `PORT` — Render sets this automatically, but Spring reads `PORT` if present
- `SPRING_PROFILES_ACTIVE` — set to `prod` for production
- `SPRING_JPA_HIBERNATE_DDL_AUTO` — recommended `validate` in prod

## Which variables to mark as Secret
- Secrets: `SPRING_DATASOURCE_PASSWORD`, `SPRING_DATASOURCE_USERNAME`, `MINIO_ACCESS_KEY`, `MINIO_SECRET_KEY`, `APP_ADMIN_PASSWORD`, `APP_ADMIN_USERNAME` (optional)

## Notes
- Avoid committing real secrets to the repo. Use the Render Secret toggle.
- Prefer DB migrations (Flyway) and `ddl-auto=validate` in production.
- Consider replacing in-memory admin with a persistent user store before public rollout.

## Troubleshooting
- If the app cannot connect to DB, try switching from `SPRING_DATASOURCE_URL` to `DB_HOST`/`DB_NAME` variants used above.
