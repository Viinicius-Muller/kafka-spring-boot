package com.apache.kafka.in.spring.service;

import com.apache.kafka.in.spring.model.DriverLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class DriverLocationProducer {

    private final KafkaTemplate kafkaTemplate;
    private final ObjectMapper objectMapper; // convert location data to JSON

    public void sendLocation(DriverLocation locationData) {
        try {
            // Convert Java object to JSON
            String jsonPayload = objectMapper.writeValueAsString(locationData);

            // topic, key, value
            kafkaTemplate.send("driver-locations", locationData.driverId(), jsonPayload);
            System.out.println("Driver location sent to Kafka topic: "+jsonPayload);
        } catch (DatabindException e) {
            e.printStackTrace();
        }
    }
}
