package com.apache.kafka.in.spring.controller;

import com.apache.kafka.in.spring.model.DriverLocation;
import com.apache.kafka.in.spring.service.DriverLocationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverLocationProducer producer;

    @PostMapping("/ping")
    public ResponseEntity<String> pingLocation(@RequestBody DriverLocation location) {
        producer.sendLocation(location);
        return ResponseEntity.ok("Ping accepted!");
    }
}
