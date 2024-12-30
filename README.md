# Учебный Проект Финтех Академии МТС Банка

Этот проект представляет собой работу выполненую в рамках домашнего задания, во время обучения в Финтех Академии МТС Банка.
## Функционал

- **Управление Базой Данных**: Проект обеспечивает работу с базой данных пользователей, используя PostgreSQL в качестве СУБД. <img src="https://www.postgresql.org/media/img/about/press/elephant.png" width="20" height="20">
- **Технологии**: Для взаимодействия с базой данных используется Spring Data JPA, а для аутентификации пользователей — Spring Security.<img src="https://spring.io/img/projects/spring-data.svg" width="20" height="20"> <img src="https://spring.io/img/projects/spring-security.svg" width="20" height="20">
- **Система Миграции**: Для управления версиями базы данных используется Flyway, который позволяет автоматически обновлять схему базы данных посредством миграций  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Flyway_logo.svg/330px-Flyway_logo.svg.png" width="20" height="20">
- **Графический Интерфейс**: Для организации работы с графическим интерфейсом используется Thymeleaf.  <img src="https://www.thymeleaf.org/images/thymeleaf.png" width="20" height="20">
- **Тестирование**: Функционал проекта покрыт JUnit тестами, с использованием Mockito. <img src="https://junit.org/junit5/assets/img/junit5-logo.png" width="20" height="20">
- **Логирование**: Для организации логов использован Logback

## Краткое описание

Часть функционала входе обучения изменялась и вырезалась, такие как создание собственного spring starter. Вырезанный функционал можно найти в других ветках.

## Начало работы

## Требования
- **Java 11**
- **Gradle**
- **Postgres**

## Сборка проекта
- Клонируйте репозиторий 
- Выставите в application.properties данные вашей базы psql
- Выполните сборку и запустите
- Перейдите в графический интерфейс на http://localhost:8090

