package com.vheekey.book_service.common;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String> details;

    public ApiError(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = new ArrayList<>();
    }

    public void addDetail(String detail) {
        this.details.add(detail);
    }
}
