package com.apache.kafka.in.spring.model;

public record DriverLocation(
        String driverId,
        double latitude,
        double longitude,
        String status
) {
}
