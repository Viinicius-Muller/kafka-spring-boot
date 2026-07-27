# Kafka in Spring

Leia em [Português](README.pt-BR.md)

A simple Spring Boot demo showing a basic Apache Kafka producer/consumer flow: a REST endpoint publishes a message to a topic, and a listener consumes it.

## Features

- `POST` endpoint to publish a message to Kafka
- `@KafkaListener` that consumes messages from the same topic and logs them
- Topic auto-creation on startup via a `NewTopic` bean

## Tech Stack

- Java 17
- Spring Boot 4.1.0
- Spring for Apache Kafka
- Spring Web (MVC)
- Lombok
- Maven

## Prerequisites

- JDK 17
- Docker (to run a local Kafka broker via `docker-compose`)

## Getting Started

1. Start Zookeeper and Kafka locally:

   ```bash
   docker-compose up -d
   ```

2. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows: `mvnw.cmd spring-boot:run`

   The app starts on `http://localhost:8080`.

## Usage

Publish a message:

```bash
curl -X POST "http://localhost:8080/kafka/publish?message=hello"
```

Check the application console — the `MessageConsumer` listener will print the received message.

## Project Structure

```
src/main/java/com/apache/kafka/in/spring/
├── Application.java              # main class
├── config/KafkaTopicConfig.java  # declares the "demo-topic" topic
├── controller/KafkaController.java  # POST /kafka/publish
└── service/
    ├── MessageProducer.java      # sends messages to Kafka
    └── MessageConsumer.java      # listens for messages
```

## Configuration

Defined in `src/main/resources/application.properties`:

| Property | Value |
|---|---|
| `server.port` | `8080` |
| `spring.kafka.bootstrap-servers` | `localhost:9092` |
| `spring.kafka.consumer.group-id` | `demo-group` |
| Kafka topic | `demo-topic` |

`docker-compose.yml` exposes Kafka on host port `9092` and Zookeeper on host port `22181`.
