package com.fabrication.services;

import com.fabrication.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailServiceImpl implements EmailService {

    @Value("spring.mail.username")
    private String sender;


    @Value("${spring.mail.username}")
    private String sendermail;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMessage(
            String to,
            String subject,
            String text
    ) throws ResourceNotFoundException {
        validateInput(to, subject, text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendermail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p>For security reason, you're required to use the following "
                        + "One Time Password to login:</p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        javaMailSender.send(message);
    }

    @Override
    public void sendMailToEmition(String to, String subject, String text) throws ResourceNotFoundException {
        validateInput(to, subject, text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendermail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        javaMailSender.send(message);

    }

    private void validateInput(String to, String subject, String text) {
        if(!to.matches("^[a-zA-Z0-9_!#%&*+=|~^-]+@[a-zA-Z0-9.-]+$")){
            throw new ResourceNotFoundException("Receiver address is not valid");
        }
        if(!subject.matches("^[a-zA-Z]{5,20}$")){
            throw new ResourceNotFoundException("Subject is not valid");
        }
        if(!text.matches("^[0123456789]{6}")){
            throw new ResourceNotFoundException("Validation Code is not valid");
        }
    }

    @Override
    public String generateValidationCode() {
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            int randIndex=rand.nextInt("0123456789".length());
            res.append("0123456789".charAt(randIndex));
        }
        return res.toString();
    }

}
