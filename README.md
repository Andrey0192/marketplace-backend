# marketplace-backend (MVP)

Spring Boot 3 + Java 17 starter backend for a simple marketplace-like product.

## Stack
- Java 17
- Spring Boot 3.x (Web, Validation, Data JPA)
- PostgreSQL
- Flyway migrations
- OpenAPI/Swagger UI (springdoc)
- Gradle build

## Run
1) Start Postgres:
```bash
docker compose up -d
```

2) Run app:
```bash
./gradlew bootRun
```

3) Swagger UI:
- http://localhost:8080/swagger-ui.html

## API
- /api/users CRUD
- /api/categories CRUD
- /api/listings CRUD + filters: categoryId, sellerId, status
- /api/orders CRUD + filters: buyerId, status

Pagination/sorting: standard Spring `Pageable` params: `page`, `size`, `sort`.
Example: `?page=0&size=20&sort=createdAt,desc`
