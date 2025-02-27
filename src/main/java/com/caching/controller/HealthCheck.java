package com.caching.controller;

import com.caching.dto.out.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Health Check API to check if the API
 * is working at a given instance.
 */
@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public ResponseEntity<GeneralResponse> healthCheck(){
        String currentTimestamp = LocalDateTime.now().toString();
        GeneralResponse response = new GeneralResponse("SUCCESS", "WebServer Running!", currentTimestamp);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
