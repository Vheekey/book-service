package com.vheekey.book_service.exceptions;

import com.vheekey.book_service.common.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Map<Class<? extends Exception>, BaseExceptionHandler<? extends Exception>> handlers;

    public GlobalExceptionHandler(List<BaseExceptionHandler<? extends Exception>> exceptionHandlers) {
        this.handlers = exceptionHandlers.stream()
                .collect(Collectors.toMap(
                        handler -> getExceptionType(handler.getClass()),
                        handler -> handler
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        return getHandler(HttpMessageNotReadableException.class).handle(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException exception) {
        return getHandler(ConstraintViolationException.class).handle(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        return getHandler(MethodArgumentNotValidException.class).handle(exception);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception) {
        return getHandler(ResourceNotFoundException.class).handle(exception);
    }

    @SuppressWarnings("unchecked")
    private <T extends Exception> BaseExceptionHandler<T> getHandler(Class<T> exceptionClass) {
        BaseExceptionHandler<T> handler = (BaseExceptionHandler<T>) handlers.get(exceptionClass);
        if (handler == null) {
            throw new IllegalStateException("No handler registered for exception: " + exceptionClass);
        }

        if (!exceptionClass.equals(getExceptionType(handler.getClass()))) {
            throw new IllegalStateException(
                    "Handler registered for exception: "
                            + exceptionClass
                            + " does not match exception type: "
                            + exceptionClass
            );
        }
        return handler;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Exception> getExceptionType(Class<?> handlerClass) {
        return Arrays.stream(handlerClass.getGenericInterfaces())
                .filter(type -> type instanceof ParameterizedType)
                .map(type -> (ParameterizedType) type)
                .filter(type -> type.getRawType().equals(BaseExceptionHandler.class))
                .map(type -> (Class<? extends Exception>) type.getActualTypeArguments()[0])
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Cannot determine exception type for handler: " + handlerClass)
                );
    }

}

