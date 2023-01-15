package com.fabrication.filters;

import com.fabrication.services.JwtAgentServiceImpl;
import com.fabrication.services.JwtClientServiceImpl;
import com.fabrication.services.JwtService;
import com.fabrication.utils.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Component
public class TraceInterceptor implements HandlerInterceptor {
    /*

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, "
                + "Access-Control-Allow-Credentials, Authorization");
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            if(request.getServletPath().contains("/login")) {
                return true;
            }else {
               if(request.getServletPath().contains("/client")){
                   String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
                   JwtService jwtTokenUtil = new JwtClientServiceImpl();
                   if(!jwtTokenUtil.validateToken(jwt)) {
                       return false;
                   }

                   try {
                       Collection<SimpleGrantedAuthority> authorities = jwtTokenUtil.getAllGrantedAuthorityFromToken(jwt);
                       String username =  jwtTokenUtil.getSubject(jwt);
                       UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(username, null, authorities);
                       SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                       return true;
                   }catch (Exception e) {
                       response.setContentType("text/plain");
                       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                       new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
                       return false;
                   }
               } else if (request.getServletPath().contains("/admin")) {
                   String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
                   JwtService jwtTokenUtil = new JwtAgentServiceImpl();
                   if(!jwtTokenUtil.validateToken(jwt)) {
                       return false;
                   }

                   try {
                       Collection<SimpleGrantedAuthority> authorities = jwtTokenUtil.getAllGrantedAuthorityFromToken(jwt);
                       String username =  jwtTokenUtil.getSubject(jwt);
                       UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(username, null, authorities);
                       SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                       return true;
                   }catch (Exception e) {
                       response.setContentType("text/plain");
                       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                       new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
                       return false;
                   }
               }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

     */

}
