package com.fabrication.client.services;

import com.fabrication.entities.Agent;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.utils.LoginBean;
import com.fabrication.utils.PersonStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceAgentTest {

    private PersonRepository agentRepositoryMock;
    private PersonService personServiceTest;

    @BeforeEach
    void setUp() {
        agentRepositoryMock = mock(PersonRepository.class);
        personServiceTest = new PersonServiceImpl(agentRepositoryMock,null) ;
    }

    @Test
    @DisplayName("it should save and agent")
    void itShouldSaveAndAgent() {
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.ACTIVE,
                "toto",
                "toto",
                "login",
                "password");
        when(agentRepositoryMock.save(any(Agent.class))).thenReturn(agent);
        personServiceTest.savePersonAgent(agent);
        verify(agentRepositoryMock,times(1)).save(agent);
    }

    @Test
    @DisplayName("it should throw an exception when login is null")
    void itShouldThrowExceptionWhenLoginNull() {
        Agent agent = new Agent(
                null,
                "String email",
                PersonStatus.ACTIVE,
                "agentLastName",
                "agentFirstName",
                null,
                "password");
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals("Login is null", exception.getMessage());
    }

    @Test
    @DisplayName("it should throw an exception when email is null")
    void itShouldThrowExceptionWhenEmailIsNull() {
        Agent agent = new Agent(
                null,
                null,
                PersonStatus.ACTIVE,
                "agentLastName",
                "agentFirstName",
                "Login",
                "password");
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals("Email is null", exception.getMessage());
    }

    @Test
    @DisplayName("it should throw an exception when password is null")
    void itShouldThrowExceptionWhenPasswordIsNull() {
        Agent agent = new Agent(
                null,
                "null",
                PersonStatus.ACTIVE,
                "agentLastName",
                "agentFirstName",
                "Login",
                null);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals("Password is null", exception.getMessage());
    }

    @Test
    @DisplayName("it should throw an exception when LastName is null")
    void itShouldThrowExceptionWhenLastNameIsNull() {
        Agent agent = new Agent(
                null,
                "xvdfvdfsv@gmail.com",
                PersonStatus.ACTIVE,
                null,
                "agentFirstName",
                "Login",
                "password");
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals("LastName is null", exception.getMessage());
    }

    @Test
    @DisplayName("it should throw an exception when firstname is null")
    void itShouldThrowExceptionWhenFirstNameIsNull() {
        Agent agent = new Agent(
                null,
                "xvdfvdfsv@gmail.com",
                PersonStatus.ACTIVE,
                "agentLastName",
                null,
                "Login",
                "password");
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals("FirstName is null", exception.getMessage());
    }

    @Test
    @DisplayName("it should throw an exception when login already exist")
    void itShouldThrowExceptionWhenLoginAlreadyExist() {
        Agent agent = new Agent(
                null,
                "to@gmail.com",
                PersonStatus.ACTIVE,
                "toto",
                "toto",
                "login",
                "password");
        when(agentRepositoryMock.findAgentByLogin(any(String.class))).thenReturn(agent);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals("Login "+agent.getLogin()+" already use", exception.getMessage());
    }

    @Test
    @DisplayName("it should throw an exception when email already exist")
    @Order(1)
    void itShouldThrowExceptionWhenEmailAlreadyExist() {
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.ACTIVE,
                "toto",
                "toto",
                "loginh",
                "password");
        when(agentRepositoryMock.findAgentByEmail(any(String.class))).thenReturn(agent);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals("Email "+agent.getEmail()+" already use", exception.getMessage());
    }

    @Test
    @DisplayName("it should throw an exception when lastname and firstname already exist")
    @Order(1)
    void itShouldThrowExceptionWhenFirstNameAndLastNamrAlreadyExist() {
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.ACTIVE,
                "toto",
                "toto",
                "loginh",
                "password");
        when(agentRepositoryMock.findAgentByLastNameAndFirstName(any(String.class), any(String.class)))
                .thenReturn(agent);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.savePersonAgent(agent)
        );
        assertEquals(
                "Agent with LastName " +agent.getAgentLastName() +" and FirstName "+agent.getAgentFirstName() +"already exist",
                exception.getMessage());
    }

    @Test
    @DisplayName("It should connect an agent with login and password")
    void itShouldConnectAnAgentWithLoginAndPassword() {
        LoginBean loginBean = new LoginBean("login","password");
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.ACTIVE,
                "toto",
                "toto",
                "login",
                "password");
        when(agentRepositoryMock.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(agent);
        assertThat(personServiceTest.connectAgent(loginBean)).isNotNull();
    }

    @Test
    @DisplayName("It should connect an agent with email and password")
    void itShouldConnectAnAgentWithEmailAnPassword() {
        LoginBean loginBean = new LoginBean("login@gmail.com","password");
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.ACTIVE,
                "toto",
                "toto",
                "login",
                "password");
        when(agentRepositoryMock.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(null);
        when(agentRepositoryMock.connectAgentByEmailAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(agent);
        assertThat(personServiceTest.connectAgent(loginBean)).isNotNull();
    }

    @Test
    @DisplayName("It should throw an exception when connect an agent because login is empty")
    void itShouldThrowAnExceptionWhenConnectAnAgentBecauseLoginIsEmpty() {
        LoginBean loginBean = new LoginBean("","password");
        when(agentRepositoryMock.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenThrow(new MockitoException("Login is null"));
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.connectAgent(loginBean)
        );
        assertEquals("Login is null", exception.getMessage());
    }


    @Test
    @DisplayName("It should throw an exception when connect an agent because password is null")
    void itShouldThrowAnExceptionWhenConnectAnAgentBecausePasswordIsNull() {
        LoginBean loginBean = new LoginBean("login",null);
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.ACTIVE,
                "toto",
                "toto",
                "login",
                "password");
        when(agentRepositoryMock.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(agent);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.connectAgent(loginBean)
        );
        assertEquals("Password is null", exception.getMessage());
    }

    @Test
    @DisplayName("It should throw an exception when connect an agent because agent don't exist")
    void itShouldThrowAnExceptionWhenConnectAnAgentBecauseAgentDoNotExist() {
        LoginBean loginBean = new LoginBean("login","password");
        when(agentRepositoryMock.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(null);
        when(agentRepositoryMock.connectAgentByEmailAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(null);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.connectAgent(loginBean)
        );
        assertEquals("Login and/or Password invalid", exception.getMessage());
    }

    @Test
    @DisplayName("It should throw an exception when connect an agent because agent account is locked")
    void itShouldThrowAnExceptionWhenConnectAnAgentBecauseAgentAccountIsLocked() {
        LoginBean loginBean = new LoginBean("login","password");
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.INACTIVE,
                "toto",
                "toto",
                "login",
                "password");
        when(agentRepositoryMock.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(agent);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.connectAgent(loginBean)
        );
        assertEquals("This account is locked", exception.getMessage());
    }

    @Test
    @DisplayName("It should throw an exception when connect an agent because agent account is locked (Email)")
    void itShouldThrowAnExceptionWhenConnectAnAgentBecauseAgentAccountIsLocked2() {
        LoginBean loginBean = new LoginBean("login","password");
        Agent agent = new Agent(
                1L,
                "to@gmail.com",
                PersonStatus.INACTIVE,
                "toto",
                "toto",
                "login",
                "password");
        when(agentRepositoryMock.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(null);
        when(agentRepositoryMock.connectAgentByEmailAndPassword(loginBean.getLogin(), loginBean.getPassword()))
                .thenReturn(agent);
        Throwable exception = assertThrows(
                Exception.class,
                () -> personServiceTest.connectAgent(loginBean)
        );
        assertEquals("This account is locked", exception.getMessage());
    }
}
