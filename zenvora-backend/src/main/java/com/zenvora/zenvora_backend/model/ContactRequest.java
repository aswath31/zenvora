package com.zenvora.zenvora_backend.model;

import lombok.Data;

@Data
public class ContactRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String inquiryType;
    private String message;
}