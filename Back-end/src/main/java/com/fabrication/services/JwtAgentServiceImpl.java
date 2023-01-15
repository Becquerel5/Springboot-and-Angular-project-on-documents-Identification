package com.fabrication.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fabrication.utils.PersonStatus;
import com.fabrication.utils.SecurityConstants;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.stream;

@Component
public class JwtAgentServiceImpl /*implements Serializable, JwtService */{
    /*
    @Override
    public Collection<SimpleGrantedAuthority> getAllGrantedAuthorityFromToken(String token) {
        token = token.replace(SecurityConstants.TOKEN_PREFIX, SecurityConstants.REMOVE)
                .replace(SecurityConstants.TOKEN_SUFIX, SecurityConstants.REMOVE);
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String[] roles = decodedJWT.getClaim(SecurityConstants.ROLES).asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return authorities;
    }


    @Override
    public String getSubject(String token) {
        token = token.replace(SecurityConstants.TOKEN_PREFIX, SecurityConstants.REMOVE)
                .replace(SecurityConstants.TOKEN_SUFIX, SecurityConstants.REMOVE);
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }


    @Override
    public String generateAuthentificationToken(PersonStatus personStatus) {
        return JWT.create().withSubject(personStatus.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME_REFRESH))
                .withClaim("role","agent")
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));
    }

    @Override
    public String generateAuthorizationToken(String userName, List<String> authorities) {
        return JWT.create().withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .withClaim(
                        SecurityConstants.ROLES,
                        authorities)
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));
    }

    @Override
    public Boolean validateToken(String jwt) {
        return jwt != null && jwt.startsWith(SecurityConstants.TOKEN_PREFIX) && jwt.endsWith(SecurityConstants.TOKEN_SUFIX);
    }

     */
}
