package com.vheekey.book_service.exceptions;

import com.vheekey.book_service.common.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

@Component
public class MethodArgumentNotValidExceptionHandler implements BaseExceptionHandler<MethodArgumentNotValidException> {
    @Override
    public ResponseEntity<ApiError> handle(MethodArgumentNotValidException exception) {
        String message = Objects.requireNonNull(exception.getBindingResult().getFieldError())
                .getDefaultMessage();
        ApiError apiError = new ApiError(message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }
}
