package com.zenvora.zenvora_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zenvora.zenvora_backend.model.BookingRequest;
import com.zenvora.zenvora_backend.model.ContactRequest;
import com.zenvora.zenvora_backend.service.EmailService;

@RestController
@RequestMapping("/api")
public class FormController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/booking")
    public ResponseEntity<String> submitBooking(@RequestBody BookingRequest request) {
        try {
            emailService.sendBookingEmail(request);
            return ResponseEntity.ok("Booking request sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send booking request: " + e.getMessage());
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<String> submitContact(@RequestBody ContactRequest request) {
        try {
            emailService.sendContactEmail(request);
            return ResponseEntity.ok("Contact inquiry sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send contact inquiry: " + e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}