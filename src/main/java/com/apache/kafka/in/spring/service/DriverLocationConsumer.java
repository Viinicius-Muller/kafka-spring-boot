package com.apache.kafka.in.spring.service;

import com.apache.kafka.in.spring.model.DriverLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class DriverLocationConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "driver-locations", groupId = "surge-pricing-group")
    public void analyzeTrafficDensity(String jsonPayload) {
        try {
            // Read JSON as Java Object
            DriverLocation location = objectMapper.readValue(jsonPayload, DriverLocation.class);

            // Logs available rides for calculation
            if ("AVAILABLE".equals(location.status())) {
                System.out.println("Logged the available driver " + location.driverId() +
                        " to calculate local heat map.");
            }
        } catch (DatabindException e) {
            System.err.println("Failed to parse: " + jsonPayload);
        }
    }
}
