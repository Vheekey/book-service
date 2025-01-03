package com.vheekey.book_service.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.vheekey.book_service.common.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class HttpMessageNotReadableHandler implements BaseExceptionHandler<HttpMessageNotReadableException> {
    @Override
    public ResponseEntity<ApiError> handle(HttpMessageNotReadableException exception) {
        String message = "Invalid request format";

        if (exception.getCause() instanceof InvalidFormatException cause) {
            if (cause.getTargetType() != null && cause.getTargetType().isEnum()) {
                String enumValues = Arrays.stream(cause.getTargetType().getEnumConstants())
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

                message = String.format("Invalid value '%s'. Allowed values are: [%s]",
                        cause.getValue(), enumValues);
            }
        }

        ApiError apiError = new ApiError(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }
}
