# Changelog

## [0.1.0] - 2025-10-28
### Added
- Project skeleton (Spring Boot, JPA, Liquibase)
- Entities: Student, Course, Enrollment
- CRUD endpoints (students)
- Coin-based purchase flow with transactional safety
- Daily scheduled job to notify students about courses starting next day using a custom thread pool
- Email sending via JavaMailSender (Mailtrap for dev)
- Swagger/OpenAPI, Actuator, Liquibase changelog
- Global error handler
