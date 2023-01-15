package com.fabrication.client.controllers;

import com.fabrication.client.services.PersonService;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.LoginBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
@CrossOrigin("*")
public class LoginController {

    private final PersonService personService;

    public LoginController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping()
    public ResponseEntity<?> connectClient(@RequestParam(name = "login") String login) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(personService.connectClient(login));
    }

    @PostMapping()
    public ResponseEntity<?> connectAgent(@RequestBody LoginBean loginBean) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(personService.connectAgent(loginBean));
    }

    /*

    private final ReturnJWTComponent returnJWTComponent;

    @Autowired
    public LoginController(ReturnJWTComponent returnJWTComponent) {
        this.returnJWTComponent = returnJWTComponent;
    }

    @GetMapping()
    public ResponseEntity<?> connectClient(@RequestParam(name = "login") String login){
        try{
            Map<String, String> mapToken = returnJWTComponent.returnJWTComponent(new LoginBean(login,""));
            return getResponseEntity(mapToken);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> connectAgent(@RequestBody LoginBean loginBean) {
        try{
            Map<String, String> mapToken = returnJWTComponent.returnJWTComponent(loginBean);
            return getResponseEntity(mapToken);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    private ResponseEntity<?> getResponseEntity(Map<String, String> mapToken) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(
                SecurityConstants.HEADER_STRING,
                mapToken.get(SecurityConstants.HEADER_STRING)
        );
        Map<String, String> idToken = new HashMap<>();
        idToken.put("RefreshToken", mapToken.get(SecurityConstants.HEADER_STRING2));
        return ResponseEntity.ok().headers(responseHeaders).body(idToken);
    }

     */

}
