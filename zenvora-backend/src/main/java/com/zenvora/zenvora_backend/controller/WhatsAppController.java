package com.zenvora.zenvora_backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.zenvora.zenvora_backend.dto.WhatsAppRequest;
import com.zenvora.zenvora_backend.dto.WhatsAppResponse;

import jakarta.annotation.PostConstruct;

@RestController
public class WhatsAppController {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.whatsapp-number}")
    private String twilioWhatsAppNumber;

    @Value("${twilio.to-number}")
    private String toNumber;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }

    @PostMapping("api/send-whatsapp")
    public WhatsAppResponse sendWhatsAppMessage(@RequestBody WhatsAppRequest request) {
        try {
            String messageBody = String.format(
                "New Inquiry:\nName: %s\nPhone: %s\nEmail: %s",
                request.getName(),
                request.getToPhone(),
                request.getEmail()
            );

            Message message = Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(twilioWhatsAppNumber),
                messageBody
            ).create();

            return new WhatsAppResponse("success", message.getSid(), null);
        } catch (Exception e) {
            return new WhatsAppResponse("error", null, e.getMessage());
        }
    }
}