package com.apache.kafka.in.spring.controller;

import com.apache.kafka.in.spring.service.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final MessageProducer messageProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(
            @RequestParam("message") String message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to Kafka topic");
    }
}
