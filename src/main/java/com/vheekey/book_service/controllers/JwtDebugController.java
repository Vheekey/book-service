package com.vheekey.book_service.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class JwtDebugController {

    // Support for GET requests with query parameter
    @GetMapping("/jwt")
    public Map<String, Object> debugJwtGet(@RequestParam String token) {
        // Remove Bearer prefix if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return debugJwtInternal(token);
    }

    // Support for POST requests with JSON body
    @PostMapping("/jwt")
    public Map<String, Object> debugJwtPost(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        if (token == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Token is required in request body");
            return error;
        }

        // Remove Bearer prefix if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return debugJwtInternal(token);
    }

    // Internal method that handles the actual JWT debugging
    private Map<String, Object> debugJwtInternal(String token) {
        Map<String, Object> result = new HashMap<>();

        try {
            // Split the JWT
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid JWT format. Expected 3 parts but got " + parts.length);
            }

            // Decode header
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            result.put("header", headerJson);

            // Try to decode payload
            try {
                String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
                result.put("payload", payloadJson);
                result.put("payload_decode_success", true);
            } catch (Exception e) {
                log.error("Failed to decode payload: {}", e.getMessage());
                result.put("payload_raw", parts[1]);
                result.put("payload_decode_error", e.getMessage());
                result.put("payload_decode_success", false);
            }

            // Add raw parts for reference
            result.put("header_raw", parts[0]);
            result.put("signature_raw", parts[2]);

            return result;
        } catch (Exception e) {
            log.error("JWT debugging error: {}", e.getMessage(), e);
            result.put("error", e.getMessage());
            return result;
        }
    }
}