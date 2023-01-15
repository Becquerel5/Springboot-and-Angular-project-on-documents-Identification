package com.fabrication.client.controllers;

import com.fabrication.client.services.PersonService;
import com.fabrication.utils.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@CrossOrigin("*")
public class ClientRestController {
    private final PersonService personService;

    @Autowired
    public ClientRestController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/disable_user")
    public ResponseEntity<?> disableUser(@RequestParam String email) {
        personService.disableClient(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validation")
    public ResponseEntity<?> validateClient(@RequestBody LoginBean loginBean) {
        personService.codeClientValidation(loginBean.getLogin(), loginBean.getPassword());
        return ResponseEntity.noContent().build();
    }
}
