package com.zenvora.zenvora_backend.dto;



public class WhatsAppRequest { // Added 'public' here
    private String toPhone;
    private String name;
    private String email;

    // Default constructor (required for Jackson deserialization)
    public WhatsAppRequest() {}

    // Getters and setters
    public String getToPhone() { return toPhone; }
    public void setToPhone(String toPhone) { this.toPhone = toPhone; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
