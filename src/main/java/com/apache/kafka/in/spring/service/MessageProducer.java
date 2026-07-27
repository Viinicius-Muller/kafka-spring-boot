package com.apache.kafka.in.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate kafkaTemplate;

    public void sendMessage(String message) {
        // Topic-name + message to send to Kafka
        kafkaTemplate.send("demo-topic", message);
        System.out.println("Message sent to Kafka topic: "+message);
    }
}
