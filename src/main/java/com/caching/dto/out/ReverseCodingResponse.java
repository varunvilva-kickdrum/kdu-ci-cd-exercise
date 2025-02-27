package com.caching.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO class for representing the response of reverse-coding.
 * This class holds final address obtained for the input coordinates
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReverseCodingResponse {
    private String address;
}
