package com.fabrication.client.services;

import com.fabrication.entities.Agent;
import com.fabrication.entities.Client;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.LoginBean;


public interface PersonService {
    void savePersonAgent(Agent agent) throws ResourceNotFoundException;
    void savePersonClient(Client client)throws ResourceNotFoundException;
    Agent connectAgent(LoginBean loginBean)throws ResourceNotFoundException;

    Agent getAgentById(Long id)throws ResourceNotFoundException;
    Client connectClient(String email)throws ResourceNotFoundException;
    void disableClient(String email)throws ResourceNotFoundException;
    boolean codeClientValidation(String email, String code)throws ResourceNotFoundException;
}
