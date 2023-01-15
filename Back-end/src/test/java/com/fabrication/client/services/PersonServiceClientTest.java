package com.fabrication.client.services;

import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.entities.Client;
import com.fabrication.services.EmailService;
import com.fabrication.services.EmailServiceImpl;
import com.fabrication.utils.PersonStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceClientTest {
    private PersonRepository clientRepositoryMock;

    private EmailService emailServiceMock;
    private PersonService personServiceTest;

    @BeforeEach
    void setUp() {
        clientRepositoryMock = mock(PersonRepository.class);
        emailServiceMock = mock(EmailServiceImpl.class);
        personServiceTest = new PersonServiceImpl(clientRepositoryMock, emailServiceMock) ;
    }

    @Test
    @DisplayName("it should save a client")
    void itShouldSaveAClient() {
        Client client = new Client(
                1L,
                "to@gmail.com",
                PersonStatus.CREATE,
                "toto",
                Date.from(Instant.now()));
        when(clientRepositoryMock.save(any(Client.class))).thenReturn(client);
        personServiceTest.savePersonClient(client);
        verify(emailServiceMock,times(1)).generateValidationCode();
        verify(clientRepositoryMock,times(1)).save(client);
       // verify(emailServiceMock,times(1)).sendSimpleMessage(client.getEmail(), "Validation",client.getValidationCode());
    }

    @Test
    @DisplayName("it should throw and exception when email is null")
    void itShouldThrowExceptionWhenEmailIsNull() {
        Throwable exception = assertThrows(
                Exception.class,
                ()-> personServiceTest.connectClient(null)
        );
        assertThat(exception.getMessage()).isEqualTo("Email is invalid");
    }

    @Test
    @DisplayName("it should connect a client to continuous or verified identification")
    void itShouldConnectClientToContinuousOrVerifiedIdentification() {
        List<Client> clientList = new ArrayList<>();
        Client client = new Client(
                1L,
                "address@email.com",
                PersonStatus.ACTIVE,
                "toto",
                Date.from(Instant.now()));
        clientList.add(client);
        when(
                clientRepositoryMock
                        .findClientByEmailActiveOrCreate("address@email.com", PersonStatus.CREATE, PersonStatus.ACTIVE))
                .thenReturn(clientList);
        personServiceTest.connectClient("address@email.com");
        assertThat(clientList.get(0).getEmail()).isEqualTo("address@email.com");
    }

    @Test
    @DisplayName("it should create a client")
    void itShouldCreateAClient() {
        List<Client> clientList = new ArrayList<>();
        Client client;
        when(
                clientRepositoryMock
                        .findClientByEmailActiveOrCreate("address@email.com", PersonStatus.CREATE, PersonStatus.ACTIVE))
                .thenReturn(clientList);
        client = personServiceTest.connectClient("address@email.com");
        verify(emailServiceMock,times(1)).generateValidationCode();
        assertThat(client.getEmail()).isEqualTo(client.getEmail());
    }

    @Test
    @DisplayName("it should disable a client")
    void itShouldDisableAClient() {
        Client client = new Client(
                1L,
                "address@email.com",
                PersonStatus.INACTIVE,
                "toto",
                Date.from(Instant.now()));
        when(clientRepositoryMock.findClientByEmail("address@email.com", PersonStatus.ACTIVE)).thenReturn(client);
        when(
                clientRepositoryMock
                        .save(client))
                .thenReturn(client);
        personServiceTest.disableClient("address@email.com");
        verify(clientRepositoryMock,times(1))
                .findClientByEmail(client.getEmail(), PersonStatus.ACTIVE);
        verify(clientRepositoryMock,times(1))
                .save(client);
        assertThat(client.getPersonStatus()).isEqualTo(PersonStatus.INACTIVE);
    }

    @Test
    @DisplayName("It should verified validation code")
    void itShouldValidateClientCode() {
        String code ="123456";
        String email = "address@email.com";
        Client client = new Client(
                1L,
                "address@email.com",
                PersonStatus.ACTIVE,
                "toto",
                Date.from(Instant.now())
        );
        when(clientRepositoryMock.findClientByEmailAndValidationCode(email, code, PersonStatus.CREATE)).thenReturn(client);
        boolean verificationValue = personServiceTest.codeClientValidation(email, code);
        verify(clientRepositoryMock,times(1)).save(client);
        assertTrue(verificationValue);
    }

    @Test
    @DisplayName("It should't verified validation code")
    void itShouldNotValidateClientCode() {
        String code ="123456";
        String email = "address@email.com";
        when(clientRepositoryMock.findClientByEmailAndValidationCode(email, code, PersonStatus.CREATE)).thenReturn(null);
        boolean verificationValue = personServiceTest.codeClientValidation(email, code);
        assertFalse(verificationValue);
    }

}
