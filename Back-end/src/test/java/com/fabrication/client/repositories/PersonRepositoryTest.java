package com.fabrication.client.repositories;

import com.fabrication.entities.Agent;
import com.fabrication.entities.Client;
import com.fabrication.utils.PersonStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository<Client> clientRepository;

    @Autowired
    PersonRepository<Agent> agentRepository;

    @BeforeEach
    void setUp(){
        List<Client> clientList = new ArrayList<>();
        clientList.add(
                new Client(
                        null,
                        "email1@gmail.com",
                        PersonStatus.CREATE,
                        "553456",
                        Date.from(Instant.now())
                )
        );
        clientList.add(
                new Client(
                        null,
                        "email@gmail.com",
                        PersonStatus.CREATE,
                        "553486",
                        Date.from(Instant.now())
                )
        );
        clientList.add(
                new Client(
                        null,
                        "emai@gmail.com",
                        PersonStatus.ACTIVE,
                        "553456",
                        Date.from(Instant.now())
                )
        );
        clientList.add(
                new Client(
                        null,
                        "mail1@gmail.com",
                        PersonStatus.CREATE,
                        "553456",
                        Date.from(Instant.now())
                )
        );
        clientRepository.saveAll(clientList);

        List<Agent> agentList = new ArrayList<>();
        agentList.add(
                new Agent(
                        null,
                        "email@gmail.com",
                        PersonStatus.CREATE,
                        "agentLastName",
                        "agentFirstName",
                        "login",
                        "password"
                )
        );
        agentList.add(
                new Agent(
                        null,
                        "emai45l@gmail.com",
                        PersonStatus.CREATE,
                        "qagentLastName",
                        "agentFirstName",
                        "login1",
                        "password"
                )
        );
        agentRepository.saveAll(agentList);

    }

    @AfterEach
    void destroyAll(){
        clientRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void givenEmail_itShouldReturnClient() {

        //given
        String email = "email1@gmail.com";

        //when
        Client client = clientRepository.findClientByEmail(email,PersonStatus.CREATE );

        //then
        assertThatClientsAreEqual(email, client);
    }

    private void assertThatClientsAreEqual(String email, Client client) {
        assertThat(client.getEmail()).isEqualTo(email);
        assertThat(client.getValidationCode()).isEqualTo("553456");
        assertThat(client.getPersonStatus()).isEqualTo(PersonStatus.CREATE);
        assertThat(client.getId()).isNotNull();

    }

    @Test
    @Order(2)
    public void givenEmail_itShouldReturnNullClient() {

        //given
        String email = "titi@gmail.com";

        //when
        Client client = clientRepository.findClientByEmail(email,PersonStatus.CREATE );

        //then
        assertThat(client).isNull();
    }

    @Test
    @Order(3)
    public void givenEmailAndValidationCode_itShouldActiveClient() {
        //given
        String email = "email1@gmail.com";
        String code = "553456";

        //when
        Client client = clientRepository.findClientByEmailAndValidationCode(email, code, PersonStatus.CREATE);

        //then

        assertThat(client).isNotNull();
    }

    @Test
    @Order(3)
    public void givenEmailAndValidationCode_itShouldNotActiveClient() {
        //given
        String email = "email1@gmail.com";
        String code = "553406";

        //when
        Client client = clientRepository.findClientByEmailAndValidationCode(email, code, PersonStatus.CREATE);

        //then

        assertThat(client).isNull();
    }

    @Test
    @Order(4)
    public void givenEmail_itShouldReturnListOfEntriesForOneClientWhenItIsCreateOrActive() {
        //given
        String email = "emai@gmail.com";

        //when
        List<Client> clientList = clientRepository.findClientByEmailActiveOrCreate(email, PersonStatus.CREATE, PersonStatus.ACTIVE);

        //then
        assertFalse(clientList.isEmpty());
    }

    @Test
    @Order(5)
    public void givenEmail_itShouldReturnNullListOfEntriesForOneClientWhenItIsCreateOrActive() {
        //given
        String email = "emaitoto@gmail.com";

        //when
        List<Client> clientList = clientRepository.findClientByEmailActiveOrCreate(email, PersonStatus.CREATE, PersonStatus.ACTIVE);

        //then
        assertTrue(clientList.isEmpty());
    }

    @Test
    @Order(6)
    void givenLogin_itShouldGetAgentByLogin() {
        //Given
        String login = "login";

        //When
        Agent agent = agentRepository.findAgentByLogin(login);

        //Then
        assertThat(agent).isNotNull();
    }

    @Test
    @Order(7)
    void givenLogin_itShouldReturnNullAgentByLogin() {
        String login = "login569";

        //When
        Agent agent = agentRepository.findAgentByLogin(login);

        //Then
        assertThat(agent).isNull();
    }

    @Test
    @Order(8)
    void givenEmail_itShouldGetAgentByEmail() {
        //Given
        String email = "emai45l@gmail.com";

        //When
        Agent agent = agentRepository.findAgentByEmail(email);

        //Then
        assertThat(agent).isNotNull();
    }

    @Test
    @Order(9)
    void givenLogin_itShouldReturnNullAgentByEmail() {
        String login = "login569emai45l@gmail.com";

        //When
        Agent agent = agentRepository.findAgentByLogin(login);

        //Then
        assertThat(agent).isNull();
    }

    @Test
    @Order(10)
    void givenLoginAndPassword_itShouldGetOneAgent() {
        //Given
        String login = "login";
        String password = "password";

        //When
        Agent agent = agentRepository.connectAgentByLoginAndPassword(login, password);

        //Then
        assertThat(agent).isNotNull();
    }

    @Test
    @Order(11)
    void givenLoginAndPassword_itShouldReturnNullAgent() {
        //Given
        String login = "login";
        String password = "passwordqdqs";

        //When
        Agent agent = agentRepository.connectAgentByLoginAndPassword(login, password);

        //Then
        assertThat(agent).isNull();
    }

    @Test
    @Order(12)
    void givenEmailAndPassword_itShouldGetOneAgent() {
        //Given
        String email = "email@gmail.com";
        String password = "password";

        //When
        Agent agent = agentRepository.connectAgentByEmailAndPassword(email, password);

        //Then
        assertThat(agent).isNotNull();
    }

    @Test
    @Order(13)
    void givenEmailAndPassword_itShouldReturnNullAgent() {
        //Given
        String email = "email@gmail.com";
        String password = "passwordqdqs";

        //When
        Agent agent = agentRepository.connectAgentByLoginAndPassword(email, password);

        //Then
        assertThat(agent).isNull();
    }


    @Test
    @Order(14)
    void givenLastNameAndFirstName_itShouldGetAnAgent() {
        //Given
        String name= "agentLastName";
        String surName = "agentFirstName";

        //When
        Agent agent = agentRepository.findAgentByLastNameAndFirstName(name, surName);

        //Then
        assertThat(agent).isNotNull();
    }

    @Test
    @Order(15)
    void givenLastNameAndFirstName_itShouldReturnNull() {
        //Given
        String name= "agentLastNamqse";
        String surName = "agentFirstName";

        //When
        Agent agent = agentRepository.findAgentByLastNameAndFirstName(name, surName);

        //Then
        assertThat(agent).isNull();
    }

}