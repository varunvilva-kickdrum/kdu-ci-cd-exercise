package com.caching.service;

import com.caching.repository.GeoCodingRepository;
import com.caching.dto.out.ForwardCodingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
/**
 * Service layer for the two endpoints forward-geocoding and
 * reverse-geocoding
 */
@Service
@Slf4j
public class GeoCodingService {

    private final GeoCodingRepository geocodingRepository;


    public GeoCodingService(GeoCodingRepository geocodingRepository) {
        this.geocodingRepository = geocodingRepository;
    }
    /**
     * @param address for getting its coordinates.
     */
    public ForwardCodingResponse forwardCoding(String address) {
        log.info("Currently in Service layer of forward coding!");
        return geocodingRepository.getCoordinates(address);
    }
     /**
      * takes coordinates as input for getting its address
      * @param lat latitude in double.
      * @param lon longitude in double
     */
    public String reverseCoding(double lat, double lon) {
        log.info("Currently in Service layer of reverse coding!");
        return geocodingRepository.getAddress(lat, lon);
    }
}

