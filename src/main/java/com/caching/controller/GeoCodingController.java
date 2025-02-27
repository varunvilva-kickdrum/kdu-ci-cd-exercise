package com.caching.controller;

import com.caching.dto.out.ForwardCodingResponse;
import com.caching.service.GeoCodingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
/**
 * Controller layer for the two endpoints forward-geocoding and
 * reverse-geocoding
 */
@RestController
@Slf4j
public class GeoCodingController {

    private final GeoCodingService geocodingService;

    public GeoCodingController(GeoCodingService geocodingService) {
        this.geocodingService = geocodingService;
    }


    @GetMapping("/geocoding")
    public ResponseEntity<ForwardCodingResponse> forwardCoding(@RequestParam @NotBlank(message = "Address cannot be blank")String address) {

        log.info("Request to fet forward coding of {}", address);
        ForwardCodingResponse response = geocodingService.forwardCoding(address);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> reverseCoding(
            @RequestParam
            @DecimalMin(value = "-90", message = "Latitude must be greater than or equal to -90")
            @DecimalMax(value="90", message="Latitude must be less than or equal to 90") double latitude,
            @RequestParam
            @DecimalMin(value="-180", message = "Longitude must be greater than or equal to -180")
            @DecimalMax(value="180", message = "Longitude must be greater than or equal to -180") double longitude) {
        log.info("Request to fet reverse coding of: {}, {}", latitude, longitude);
        String response = geocodingService.reverseCoding(latitude, longitude);
        return ResponseEntity.ok(response);
    }

}
