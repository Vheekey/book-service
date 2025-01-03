package com.vheekey.book_service.exceptions;

import com.vheekey.book_service.common.ApiError;
import org.springframework.http.ResponseEntity;

public interface BaseExceptionHandler<T extends Exception> {
    ResponseEntity<ApiError> handle(T exception);
}
