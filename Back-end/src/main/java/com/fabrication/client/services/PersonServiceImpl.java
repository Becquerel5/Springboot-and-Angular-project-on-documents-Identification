package com.fabrication.client.services;

import com.fabrication.entities.Agent;
import com.fabrication.entities.Client;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.services.EmailService;
import com.fabrication.utils.LoginBean;
import com.fabrication.utils.PersonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository<Agent> agentRepository;
    private final PersonRepository<Client> clientRepository;
    private final EmailService emailService;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, EmailService emailService) {
        this.clientRepository = personRepository;
        this.agentRepository = personRepository;
        this.emailService = emailService;
    }
    @Override
    public void savePersonAgent(Agent agent) throws ResourceNotFoundException {
        throwAndExceptionForAgentInvalidInput(agent);
        agent.setId(null);
        agent.setPersonStatus(PersonStatus.ACTIVE);
        agentRepository.save(agent);
    }

    private void throwAndExceptionForAgentInvalidInput(Agent agent) {
        loginBeanException(agent.getLogin() == null || agent.getLogin().trim().equals(""), "Login is null", agent.getEmail() == null || agent.getEmail().trim().equals(""), "Email is null");
        loginBeanException(agent.getAgentFirstName() == null || agent.getAgentFirstName().trim().equals(""), "FirstName is null", agent.getAgentLastName() == null || agent.getAgentLastName().trim().equals(""), "LastName is null");
        loginBeanException(agent.getPassword() == null || agent.getPassword().trim().equals(""), "Password is null", agentRepository.findAgentByLogin(agent.getLogin()) != null, "Login " + agent.getLogin() + " already use");
        loginBeanException(agentRepository.findAgentByEmail(agent.getEmail()) != null, "Email " + agent.getEmail() + " already use", agentRepository.findAgentByLastNameAndFirstName(agent.getAgentLastName(), agent.getAgentFirstName()) != null, "Agent with LastName " + agent.getAgentLastName() + " and FirstName " + agent.getAgentFirstName() + "already exist");
    }

    @Override
    public void savePersonClient(Client client) throws ResourceNotFoundException {
        client.setId(null);
        client.setPersonStatus(PersonStatus.CREATE);
        client.setValidationCode(emailService.generateValidationCode());
        client.setDateObtainingCode(Date.from(Instant.now()));
        clientRepository.save(client);
        try{
            emailService.sendSimpleMessage(client.getEmail(), "Validation",client.getValidationCode());
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
    @Override
    public Agent connectAgent(LoginBean loginBean) throws ResourceNotFoundException {
        loginBeanException(
                loginBean.getLogin() == null | loginBean.getLogin().trim().equals(""),
                "Login is null",
                loginBean.getPassword() == null | loginBean.getLogin().trim().equals(""),
                "Password is null"
        );
        Agent agent = agentRepository.connectAgentByLoginAndPassword(loginBean.getLogin(), loginBean.getPassword());
        if(agent==null){
            agent = agentRepository.connectAgentByEmailAndPassword(loginBean.getLogin(), loginBean.getPassword());
            if(agent==null){
                throw new ResourceNotFoundException("Login and/or Password invalid");
            }else if(agent.getPersonStatus()==PersonStatus.INACTIVE){
                throw new ResourceNotFoundException("This account is locked");
            }
        }else if(agent.getPersonStatus()==PersonStatus.INACTIVE){
            throw new ResourceNotFoundException("This account is locked");
        }
        return agent;
    }

    @Override
    public Agent getAgentById(Long id) throws ResourceNotFoundException {
        Optional<Agent> optionalAgent = agentRepository.getAgentById(id);
        if(!optionalAgent.isPresent()){
            throw new ResourceNotFoundException("Agent non exist");
        }
        return optionalAgent.get();
    }

    private void loginBeanException(boolean loginBean, String Login_is_null, boolean loginBean1, String Password_is_null) {
        if(loginBean){
            throw new ResourceNotFoundException(Login_is_null);
        }
        if(loginBean1){
            throw new ResourceNotFoundException(Password_is_null);
        }
    }

    @Override
    public Client connectClient(String email) throws ResourceNotFoundException {
        if(email==null || email.trim().equals("") || !email.matches("^[a-zA-Z0-9_!#%&*+=|~^-]+@[a-zA-Z0-9.-]+$")){
            throw new ResourceNotFoundException("Email is invalid");
        }
        List<Client> clientList;
        clientList = clientRepository.findClientByEmailActiveOrCreate(email, PersonStatus.CREATE, PersonStatus.ACTIVE);
        if(clientList.isEmpty()){
            Client client = new Client();
            client.setEmail(email);
            this.savePersonClient(client);
            return client;
        }else{
            return clientList.get(0);
        }
    }

    @Override
    public void disableClient(String email) {
        Client client;
        client = clientRepository.findClientByEmail(email, PersonStatus.ACTIVE);
        client.setPersonStatus(PersonStatus.INACTIVE);
        clientRepository.save(client);
    }

    @Override
    public boolean codeClientValidation(String email, String code) {
        Client client = clientRepository.findClientByEmailAndValidationCode(email, code, PersonStatus.CREATE);
        if(client==null)
            return false;
        client.setPersonStatus(PersonStatus.ACTIVE);
        clientRepository.save(client);
        return true;
    }

}
