package com.fabrication.components;

//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginComponent /*implements AuthenticationProvider*/ {
/*
    private final PersonService personService;

    @Autowired
    public LoginComponent(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getCredentials().toString().trim()=="" || authentication.getCredentials()==null){
            LoginBean loginBean = new LoginBean(authentication.getName(), null);
            try {
                personService.connectClient(loginBean.getLogin());
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("CLIENT"));
                UserDetails userDetails = new User(loginBean.getLogin() ,""
                        ,grantedAuthorities);
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                        "", grantedAuthorities);
                return auth;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            LoginBean loginBean = new LoginBean(authentication.getName(), authentication.getCredentials().toString());
            try {
                personService.connectAgent(loginBean);
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("AGENT"));
                UserDetails userDetails = new User(authentication.getName() ,authentication.getCredentials().toString()
                        ,grantedAuthorities);
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                        authentication.getCredentials().toString() , grantedAuthorities);
                return auth;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

 */
}
