package com.fabrication.services;

import com.fabrication.utils.PersonStatus;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface JwtService {
  //  public Collection<SimpleGrantedAuthority> getAllGrantedAuthorityFromToken(String token);
    public String  getSubject(String token);
    public String generateAuthentificationToken(PersonStatus personStatus);
    public String generateAuthorizationToken(String userName,  List<String> authorities);
    public Boolean validateToken(String jwt);
}
