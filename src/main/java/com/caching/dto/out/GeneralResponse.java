package com.caching.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO class for representing the response of General Response for Errors, Exceptions, etc.
 * This class holds the status, message and the timestamp for maintainability.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GeneralResponse {
    private String status;
    private String message;
    private String timestamp;
}
