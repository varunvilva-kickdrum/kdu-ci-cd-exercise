package com.caching.exception;

import com.caching.dto.out.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String FAILURE = "FAILURE";
    /**
     * Handles exceptions when validation on a method argument annotated with @Valid fails.
     *
     * @param ex The {@link MethodArgumentNotValidException} thrown during validation.
     * @return A {@link ResponseEntity} containing a {@link GeneralResponse} with error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse>handleInvalidArgumentException(MethodArgumentNotValidException ex){
        String currentTimestamp = LocalDateTime.now().toString();
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        GeneralResponse error = new GeneralResponse(FAILURE, errorMessage, currentTimestamp);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles custom exceptions for invalid addresses.
     *
     * @param ex The {@link InvalidAddressException} thrown when an address is invalid.
     * @return A {@link ResponseEntity} containing a {@link GeneralResponse} with error details.
     */
    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<GeneralResponse> handleInvalidAddressException(InvalidAddressException ex) {
        String currentTimestamp = LocalDateTime.now().toString();
        GeneralResponse error = new GeneralResponse(FAILURE, ex.getMessage(), currentTimestamp);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    /**
     * Handles exceptions when a method argument has a type mismatch.
     *
     * @param ex The {@link MethodArgumentTypeMismatchException} thrown when argument types do not match.
     * @return A {@link ResponseEntity} containing a {@link GeneralResponse} with error details.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GeneralResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = ex.getMessage();
        GeneralResponse errorResponse = new GeneralResponse(FAILURE, errorMessage, LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }
}
