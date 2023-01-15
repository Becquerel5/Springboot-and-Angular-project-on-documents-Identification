package com.fabrication.client.repositories;

import com.fabrication.entities.Agent;
import com.fabrication.entities.Client;
import com.fabrication.entities.Person;
import com.fabrication.utils.PersonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository<T extends Person> extends JpaRepository<T, Long>{
    @Query("from Client c where c.email = ?1 and c.personStatus = ?3 and c.validationCode = ?2")
    Client findClientByEmailAndValidationCode(String email, String code, PersonStatus status);

    @Query("from Client c where c.email = ?1 and c.personStatus = ?2")
    Client findClientByEmail(String email, PersonStatus status);

    @Query("delete from Client c where c.email = ?1 and c.validationCode = ?2 and c.personStatus = ?3")
    boolean deleteClientRegistry(String email, String code, PersonStatus personStatus);


    @Query("from Client c where c.email = ?1 and (c.personStatus = ?2 or c.personStatus =?3)")
    List<Client> findClientByEmailActiveOrCreate(String email, PersonStatus statusCreate, PersonStatus statusActive);



    @Query("from Agent a where a.login = ?1")
    Agent findAgentByLogin(String login);

    @Query("from Agent a where a.email = ?1")
    Agent findAgentByEmail(String email);

    @Query("from Agent a where a.login = ?1 and a.password = ?2")
    Agent connectAgentByLoginAndPassword(String login, String password);

    @Query("from Agent a where a.id = ?1")
    Optional<Agent> getAgentById(Long id);

    @Query("from Agent a where a.email = ?1 and a.password = ?2")
    Agent connectAgentByEmailAndPassword(String email, String password);

    @Query("from Agent a where a.agentLastName = ?1 and a.agentFirstName = ?2")
    Agent findAgentByLastNameAndFirstName(String lastName, String firstName);

    @Query("from Agent a where a.id = ?1")
    Agent findAgentById(Long id);

    @Query("from Client c where c.id = ?1")
    Client findClientById(Long id);

}
