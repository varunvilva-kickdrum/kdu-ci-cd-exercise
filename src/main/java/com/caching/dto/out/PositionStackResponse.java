package com.caching.dto.out;

import com.caching.model.PositionData;
import lombok.Data;

import java.util.List;

/**
 * DTO class for representing the response of external API.
 * This class holds a list of Position Data
 */
@Data
public class PositionStackResponse {
    private List<PositionData> data;
}
