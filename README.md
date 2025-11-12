
# kcb-demo

Spring Boot 3 API called **kcb-demo** that runs anywhere with **Docker Compose**.
Includes h2, Flyway migrations, health checks

## Stack
- Java 17, Spring Boot 3.3.x, Gradle 8.x
- Web, Validation, JPA, Actuator
- H2 + Flyway(easy db migration)
- Dockerfile (multi-stage), Docker Compose
- Makefile conveniences
- JUnit 5 test starter

---

## Quickstart (Docker Compose)

1. Build & run:
   ```bash
   docker compose up --build
   ```
2. Verify:
   ```bash
   curl -s -u user:password http://localhost:8080/api/books
   curl http://localhost:8080/actuator/health
   ```
3. Swagger:
   ```bash
   http://localhost:8080/swagger-ui/index.html
   ```
Stop:
```bash
docker compose down       
docker compose down -v      
```


