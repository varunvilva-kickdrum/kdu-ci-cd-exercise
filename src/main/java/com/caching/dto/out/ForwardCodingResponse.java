package com.caching.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO class for representing the response of forward-coding.
 * This class holds the latitude and longitude of a location.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForwardCodingResponse {
    private double latitude;
    private double longitude;
}
