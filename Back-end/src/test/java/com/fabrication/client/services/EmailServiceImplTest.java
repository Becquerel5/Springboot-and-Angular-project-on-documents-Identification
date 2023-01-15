package com.fabrication.client.services;

import com.fabrication.services.EmailService;
import com.fabrication.services.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {

    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String sender;

    private JavaMailSender javaMailSenderMock;

    @BeforeEach
    void setUp(){
        javaMailSenderMock = mock(JavaMailSenderImpl.class);
        emailService = new EmailServiceImpl(javaMailSenderMock);
    }

    @Test
    @DisplayName("it should send simple message")
    void itShouldSendSimpleMessageTest() {
        // System.err.println(sender);
        String to = "addres@gmail.com";
        String subject = "Validation";
        String text = "123456";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p>For security reason, you're required to use the following "
                        + "One Time Password to login:</p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        emailService.sendSimpleMessage(to,subject,text);
        verify(javaMailSenderMock,times(1)).send(message);
    }

    @Test
    @DisplayName("it should throw an exception when receiver is invalid")
    void itShouldThrowAnExceptionWhenReceiverIsInvalid() {
        String to = "hghg";
        String subject = "Validation";
        String text = "123456";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p>For security reason, you're required to use the following "
                        + "One Time Password to login:</p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        Throwable exception = assertThrows(
                Exception.class,
                ()-> emailService.sendSimpleMessage(to, subject, text)
        );
        assertThat(exception.getMessage())
                .isEqualTo("Receiver address is not valid");
        verify(javaMailSenderMock,times(0)).send(message);
    }

    @Test
    @DisplayName("it should throw an exception when subject is empty")
    void itShouldThrowAnExceptionWhenSubjectIsEmpty() {
        String to = "address@gmail.com";
        String subject = "";
        String text = "123456";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p>For security reason, you're required to use the following "
                        + "One Time Password to login:</p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        Throwable exception = assertThrows(
                Exception.class,
                ()-> emailService.sendSimpleMessage(to, subject, text)
        );
        assertThat(exception.getMessage())
                .isEqualTo("Subject is not valid");
        verify(javaMailSenderMock,times(0)).send(message);
    }


    @Test
    @DisplayName("it should throw an exception when code is not valid")
    void itShouldThrowAnExceptionWhenCodeIsNotValid() {
        String to = "address@gmail.com";
        String subject = "Validation";
        String text = "12345jkgjkhgkj";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p>For security reason, you're required to use the following "
                        + "One Time Password to login:</p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        Throwable exception = assertThrows(
                Exception.class,
                ()-> emailService.sendSimpleMessage(to, subject, text)
        );
        assertThat(exception.getMessage())
                .isEqualTo("Validation Code is not valid");
        verify(javaMailSenderMock,times(0)).send(message);
    }



    @Test
    @DisplayName("it should send mail to notified client")
    void itShouldSendEmailToNotifiedClientTest() {
        // System.err.println(sender);
        String to = "addres@gmail.com";
        String subject = "Emition";
        String text = "125478";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        emailService.sendMailToEmition(to,subject,text);
        verify(javaMailSenderMock,times(1)).send(message);
    }

    @Test
    void itShouldThrowAnExceptionWhenReceiverIsInvalidSendMailToEmit() {
        String to = "hghg";
        String subject = "Validation";
        String text = "123456";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        Throwable exception = assertThrows(
                Exception.class,
                ()-> emailService.sendMailToEmition(to, subject, text)
        );
        assertThat(exception.getMessage())
                .isEqualTo("Receiver address is not valid");
        verify(javaMailSenderMock,times(0)).send(message);
    }

    @Test
    void itShouldThrowAnExceptionWhenSubjectIsEmptySendMailToEmition() {
        String to = "address@gmail.com";
        String subject = "";
        String text = "123456";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        Throwable exception = assertThrows(
                Exception.class,
                ()-> emailService.sendMailToEmition(to, subject, text)
        );
        assertThat(exception.getMessage())
                .isEqualTo("Subject is not valid");
        verify(javaMailSenderMock,times(0)).send(message);
    }


    @Test
    void itShouldThrowAnExceptionWhenCodeIsNotValidSendMailToEmition() {
        String to = "address@gmail.com";
        String subject = "Validation";
        String text = "12345jkgjkhgkj";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        Throwable exception = assertThrows(
                Exception.class,
                ()-> emailService.sendMailToEmition(to, subject, text)
        );
        assertThat(exception.getMessage())
                .isEqualTo("Validation Code is not valid");
        verify(javaMailSenderMock,times(0)).send(message);
    }

    @Test
    @DisplayName("it should generate validation code")
    void itShouldGenerateValidationCode() {
        assertTrue(emailService.generateValidationCode().matches("^[0123456789]{6}"));
    }
}