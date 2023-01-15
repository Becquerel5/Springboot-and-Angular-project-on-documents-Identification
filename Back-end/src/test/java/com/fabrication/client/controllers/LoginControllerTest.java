package com.fabrication.client.controllers;

import com.fabrication.client.services.PersonService;
import com.fabrication.client.services.PersonServiceImpl;
import com.fabrication.entities.Agent;
import com.fabrication.entities.Client;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.LoginBean;
import com.fabrication.utils.PersonStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    private LoginController loginController;
    private PersonService personService;

    @BeforeEach
    void setUp(){
        personService = mock(PersonServiceImpl.class);
        loginController = new LoginController(personService);
    }

    @Test
    void givenEmail_itShouldConnectClient() {
        //Given
        String login = "tototo@gmail.com";
        Client client = new Client(
                1L,
                "tototo@gmail.com",
                PersonStatus.ACTIVE,
                "012345",
                Date.from(Instant.now())
        );

        //When
        when(personService.connectClient(login)).thenReturn(client);

        ResponseEntity<?> responseEntity = loginController.connectClient(login);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(client);
    }

    @Test
    void givenNullEmail_itShouldThrowAnExceptionToConnectClient() {
        //When
        when(personService.connectClient("email")).thenThrow(new ResourceNotFoundException("Email is null"));

        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()-> loginController.connectClient("email")
        );

        //Then
        assertThat(exception.getMessage()).isEqualTo("Email is null");
    }

    @Test
    void givenLoginAndPassword_itShouldConnectAgent() {
        //Given
        LoginBean loginBean = new LoginBean("login", "password");

        //When
        Agent agent = new Agent(
                1L,
                "tototo@gmail.com",
                PersonStatus.ACTIVE,
                "agentLastName",
                "agentFirstName",
                "login",
                "password"
        );
        when(personService.connectAgent(loginBean)).thenReturn(agent);
        ResponseEntity<?> responseEntity = loginController.connectAgent(loginBean);

        //Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(agent);
    }

    @Test
    void givenLogin_itShouldThrowAnExceptionToConnectAgent() {
        //Given
        LoginBean loginBean = new LoginBean("login", "password");

        //When
        when(personService.connectAgent(loginBean)).thenThrow(new ResourceNotFoundException("Login and/or Password invalid"));

        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()-> loginController.connectAgent(loginBean)
        );

        //Then
        assertThat(exception.getMessage()).isEqualTo("Login and/or Password invalid");
    }
}