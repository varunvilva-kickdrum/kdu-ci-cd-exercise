package com.caching.exception;

/**
 * Exception for invalid address input in forward-geocoding
 */
public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String message) {
        super(message);
    }
}
