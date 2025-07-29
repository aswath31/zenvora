package com.zenvora.zenvora_backend.model;

import lombok.Data;

@Data
public class BookingRequest {
    private String from;
    private String to;
    private String departureDate;
    private String returnDate;
    private String passengers;
}