package com.fabrication.services;

import com.fabrication.exceptions.ResourceNotFoundException;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text) throws ResourceNotFoundException;
    public void sendMailToEmition(String to, String subject, String text) throws ResourceNotFoundException;
    public String generateValidationCode();
}
