
# kcb-demo

A production-ready Spring Boot 3 API called **kcb-demo** that runs anywhere with **Docker Compose**.
Includes h2, Flyway migrations, health checks, and a minimal CRUD (Todo).

## Stack
- Java 17, Spring Boot 3.3.x, Gradle 8.x
- Web, Validation, JPA, Actuator
- H2 + Flyway(easy db migration)
- Dockerfile (multi-stage), Docker Compose
- Makefile conveniences
- JUnit 5 test starter
- 

---

## Quickstart (Docker Compose)

1. Use .env
2. Build & run:
   ```bash
   docker compose up --build
   ```
3. Verify:
   ```bash
   curl --location 'http://localhost:8080/api/books' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--header 'Cookie: JSESSIONID=D4DFBBBBEE39C5CACF1AB5D1B57144B9'
   curl http://localhost:8080/actuator/health
   ```

Stop:
```bash
docker compose down        # stop only
docker compose down -v     # stop + remove DB volume
```


