package com.caching.model;


import lombok.Data;

/**
 * Model class representing the position data returned by the PositionStack API.
 * This class contains details about a location, such as its label and coordinates.
 */
@Data
public class PositionData {
    private String label;
    private String latitude;
    private String longitude;
}