package com.zenvora.zenvora_backend.dto;



public class WhatsAppResponse {
    private final String status;    // Using final to enforce immutability
    private final String messageSid; // Using final to enforce immutability
    private final String error;     // Using final to enforce immutability

    // Default constructor (required for Jackson serialization)
    public WhatsAppResponse() {
        this.status = null;
        this.messageSid = null;
        this.error = null;
    }

    public WhatsAppResponse(String status, String messageSid, String error) {
        this.status = status;
        this.messageSid = messageSid;
        this.error = error;
    }

    // Getters
    public String getStatus() { return status; }
    public String getMessageSid() { return messageSid; }
    public String getError() { return error; }
}