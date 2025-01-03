package com.vheekey.book_service.exceptions;

import com.vheekey.book_service.common.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ValidationExceptionHandler implements BaseExceptionHandler<ConstraintViolationException> {
    @Override
    public ResponseEntity<ApiError> handle(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ":" + violation.getMessage())
                .collect(Collectors.joining(", "));

        ApiError apiError = new ApiError(message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }
}
