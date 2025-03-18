package com.vheekey.book_service.exceptions;

import com.vheekey.book_service.common.ApiError;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResourceNotFoundExceptionHandler implements BaseExceptionHandler<ResourceNotFoundException> {
    @Override
    public ResponseEntity<ApiError> handle(ResourceNotFoundException exception) {
        String message = exception.getMessage();
        ApiError apiError = new ApiError(message);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }
}
