# Documentation

## Requisites

In your computer you need the follow applications:

- Docker
- Docker compose

## Optional requisites

In your computer you need the follow applications:

- Database viewer (For see the data saved)
- Docker UI (For see the process, and connect more easily to the redis console for check the keys saved)

## How to use the app

Once you stay in the root project please enter in your command line the next command:

```
docker compose up
```

For demonstrative proposal the docker-compose.yml has the entire configuration (environments) for built-up the project in production mode. The right way is put this environments in a vault service for be a #cybersec compliance.

# Collection

For request our resources you need to import the next collection:
```
https://elements.getpostman.com/redirect?entityId=19317917-099e1546-da44-459a-8fc3-363b644375a9&entityType=collection
```

## Architecture

This project has a hexagonal architecture for separate the domain layer from the adapters layer. And each individual domain has a separated context, every module can communicate with another through use cases.

## Docker Hub

Public repository:

```
https://hub.docker.com/repository/docker/mtsluna/challenge/general
```

## Technologies

- Java 17 with Spring Boot 3.0.4
- PostgreSQL.
- Redis with Jedis & Redisson adapters.
- Bucket4j for implement a rate limit.
- JUnit 5, Mockito and Jpa Tests with H2.
- Mmock for local mocks

## Coverage

This project has the maximum amount of coverage. In this demo I exclude the utility class like, constants, beans, exceptions, etc.