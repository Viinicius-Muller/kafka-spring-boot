# Kafka in Spring

Read in [English](README.md)

Um demo simples em Spring Boot que mostra um fluxo básico de produtor/consumidor com Apache Kafka: um endpoint REST publica uma mensagem em um tópico, e um listener a consome.

## Funcionalidades

- Endpoint `POST` para publicar uma mensagem no Kafka
- `@KafkaListener` que consome mensagens do mesmo tópico e as registra no console
- Criação automática do tópico na inicialização, via um bean `NewTopic`

## Tecnologias

- Java 17
- Spring Boot 4.1.0
- Spring for Apache Kafka
- Spring Web (MVC)
- Lombok
- Maven

## Pré-requisitos

- JDK 17
- Docker (para rodar um broker Kafka local via `docker-compose`)

## Como executar

1. Suba o Zookeeper e o Kafka localmente:

   ```bash
   docker-compose up -d
   ```

2. Execute a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

   No Windows: `mvnw.cmd spring-boot:run`

   A aplicação sobe em `http://localhost:8080`.

## Uso

Publique uma mensagem:

```bash
curl -X POST "http://localhost:8080/kafka/publish?message=ola"
```

Verifique o console da aplicação — o listener `MessageConsumer` vai exibir a mensagem recebida.

## Estrutura do projeto

```
src/main/java/com/apache/kafka/in/spring/
├── Application.java              # classe principal
├── config/KafkaTopicConfig.java  # declara o tópico "demo-topic"
├── controller/KafkaController.java  # POST /kafka/publish
└── service/
    ├── MessageProducer.java      # envia mensagens para o Kafka
    └── MessageConsumer.java      # consome mensagens
```

## Configuração

Definida em `src/main/resources/application.properties`:

| Propriedade | Valor |
|---|---|
| `server.port` | `8080` |
| `spring.kafka.bootstrap-servers` | `localhost:9092` |
| `spring.kafka.consumer.group-id` | `demo-group` |
| Tópico Kafka | `demo-topic` |

O `docker-compose.yml` expõe o Kafka na porta `9092` e o Zookeeper na porta `22181` do host.
