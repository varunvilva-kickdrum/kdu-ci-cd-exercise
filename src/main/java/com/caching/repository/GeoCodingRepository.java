package com.caching.repository;

import com.caching.dto.out.ForwardCodingResponse;
import com.caching.dto.out.PositionStackResponse;
import com.caching.dto.out.ReverseCodingResponse;
import com.caching.exception.GlobalExceptionHandler;
import com.caching.exception.InvalidAddressException;
import com.caching.model.PositionData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Repository class for handling geocoding and reverse geocoding operations.
 * This class uses caching to optimize API calls to PositionStack.
 */
@Slf4j
@Repository
public class GeoCodingRepository {
    /**
     * API key for authenticating requests to the PositionStack API.
     */
    @Value("${api-key}")
    private String apiKey;
    /**
     * RestTemplate instance for making HTTP requests.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Fetches the geographic coordinates (latitude and longitude) for a given address.
     * Caches the response to avoid redundant API calls.
     *
     * @param address The address to geocode.
     * @return A {@link ForwardCodingResponse} containing latitude and longitude.
     * @throws InvalidAddressException If the address is invalid or no results are found.
     */
    @Cacheable(cacheNames = "geocoding", key = "#address", unless="#address.equalsIgnoreCase('goa')")
    public ForwardCodingResponse getCoordinates(String address) {
        log.info("Calling API for forward coding");
        String url = String.format("http://api.positionstack.com/v1/forward?access_key=%s&query=%s", apiKey, address);
        PositionStackResponse apiResponse = restTemplate.getForObject(url, PositionStackResponse.class);

        if(apiResponse == null || apiResponse.getData().isEmpty()) {
          throw new InvalidAddressException("The address given is invalid. Unable to find it's geocoding.");
        }
        PositionData data = apiResponse.getData().get(0);
        return new ForwardCodingResponse(Double.parseDouble(data.getLatitude()), Double.parseDouble(data.getLongitude()));

    }

    /**
     * Fetches the address for given geographic coordinates (latitude and longitude).
     * Caches the response to avoid redundant API calls.
     *
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return The address as a {@link String}.
     * Returns "Unknown Address" if no results are found.
     */
    @Cacheable(cacheNames = "reverse-geocoding", key = "{#latitude,#longitude}")
    public String getAddress(double latitude, double longitude) {

        log.info("Calling API for reverse coding");
        String url = String.format("http://api.positionstack.com/v1/reverse?access_key=%s&query=%s,%s", apiKey, latitude, longitude);
        PositionStackResponse apiResponse = restTemplate.getForObject(url, PositionStackResponse.class);

        // Check for valid response and extract the address
        if (apiResponse != null && !apiResponse.getData().isEmpty()) {
            PositionData data = apiResponse.getData().get(0);
            return data.getLabel();
        }

        // Return a default response if no data is found
        return "Unknown Address";
    }
}

