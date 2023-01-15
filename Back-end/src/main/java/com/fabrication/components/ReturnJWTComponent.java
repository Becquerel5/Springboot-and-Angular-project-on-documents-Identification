package com.fabrication.components;


//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.stereotype.Component;


//@Component
public class ReturnJWTComponent {
    /*
    private final AuthenticationManager authenticationManager;

    private final PersonService personService;

    private JwtClientServiceImpl jwtClientService;

    private JwtAgentServiceImpl jwtAgentService;

    @Autowired
    public ReturnJWTComponent(
            AuthenticationManager authenticationManager,
            PersonService personService,
            JwtClientServiceImpl jwtClientService,
            JwtAgentServiceImpl jwtAgentService) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.jwtClientService = jwtClientService;
        this.jwtAgentService = jwtAgentService;
    }

    public Map<String ,String> returnJWTComponent(LoginBean loginBean)throws Exception{
        Map<String, String> tokenList = new HashMap<>();
        if(loginBean.getPassword().trim()=="" || loginBean.getPassword() ==null){
            Client client = personService.connectClient(loginBean.getLogin());
            tokenList.put(
                    SecurityConstants.HEADER_STRING2,
                    jwtClientService.generateAuthentificationToken(client.getPersonStatus())
            );
            tokenList.put(
                    SecurityConstants.HEADER_STRING,
                    SecurityConstants.TOKEN_PREFIX
                            + jwtClientService.generateAuthorizationToken(client.getEmail(), Arrays.asList("CLIENT"))
                            + SecurityConstants.TOKEN_SUFIX
            );
        }else{
            Agent agent = personService.connectAgent(loginBean);
            tokenList.put(
                    SecurityConstants.HEADER_STRING2,
                    jwtAgentService.generateAuthentificationToken(agent.getPersonStatus())
            );
            tokenList.put(
                    SecurityConstants.HEADER_STRING,
                    SecurityConstants.TOKEN_PREFIX + jwtAgentService.generateAuthorizationToken(agent.getLogin(), Arrays.asList("AGENT"))+ SecurityConstants.TOKEN_SUFIX
            );
        }
        return tokenList;
    }

     */

}
