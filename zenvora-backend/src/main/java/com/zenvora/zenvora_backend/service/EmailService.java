package com.zenvora.zenvora_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.zenvora.zenvora_backend.model.BookingRequest;
import com.zenvora.zenvora_backend.model.ContactRequest;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail; // Sender's email from application.properties

    @Value("${spring.mail.recipient}")
    private String recipientEmail; // Recipient email from application.properties

    public void sendBookingEmail(BookingRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // Use configured sender email
        message.setTo(recipientEmail); // Use configured recipient email
        message.setSubject("New Booking Request from Flyzenvora");
        message.setText(
                "New Booking Request:\n" +
                        "From: " + request.getFrom() + "\n" +
                        "To: " + request.getTo() + "\n" +
                        "Departure Date: " + request.getDepartureDate() + "\n" +
                        "Return Date: " + (request.getReturnDate() != null ? request.getReturnDate() : "N/A") + "\n" +
                        "Passengers: " + request.getPassengers()
        );
        try {
            mailSender.send(message);
            System.out.println("Booking email sent successfully");
        } catch (Exception e) {
            System.out.println("Failed to send booking email: " + e.getMessage());
            throw e; // Re-throw to be handled by the controller
        }
    }

    public void sendContactEmail(ContactRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // Use configured sender email
        message.setTo(recipientEmail); // Use configured recipient email
       // message.setReplyTo(request.getEmail()); // Use submitter's email for replies
        message.setSubject("New Contact Inquiry from Flyzenvora");
        message.setText(
                "New Contact Inquiry:\n" +
                        "Name: " + request.getFirstName() + " " + request.getLastName() + "\n" +
                        "Email: " + request.getEmail() + "\n" +
                        "Phone: " + request.getCountryCode() + request.getPhoneNumber() + "\n" +
                        "Inquiry Type: " + request.getInquiryType() + "\n" +
                        "Message: " + request.getMessage()
        );
        try {
            mailSender.send(message);
            System.out.println("Contact email sent successfully");
        } catch (Exception e) {
            System.out.println("Failed to send contact email: " + e.getMessage());
            throw e; // Re-throw to be handled by the controller
        }
    }
}