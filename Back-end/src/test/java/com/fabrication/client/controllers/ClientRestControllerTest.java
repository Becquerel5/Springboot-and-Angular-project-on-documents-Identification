package com.fabrication.client.controllers;

import com.fabrication.client.services.PersonService;
import com.fabrication.client.services.PersonServiceImpl;
import com.fabrication.utils.LoginBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientRestControllerTest {

    private ClientRestController clientRestController;
    private PersonService personService;

    @BeforeEach
    void setUp(){
        personService = mock(PersonServiceImpl.class);
        clientRestController = new ClientRestController(personService);
    }

    @Test
    void itShouldDisableClient() {
        ResponseEntity<?> responseEntity = clientRestController.disableUser("email");
        verify(personService, times(1)).disableClient("email");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    void itShouldValidateClientCode() {
        LoginBean loginBean = new LoginBean("login","123456");
        ResponseEntity<?> responseEntity = clientRestController.validateClient(loginBean);
        verify(personService, times(1)).codeClientValidation(loginBean.getLogin(),loginBean.getPassword());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isNull();
    }
}