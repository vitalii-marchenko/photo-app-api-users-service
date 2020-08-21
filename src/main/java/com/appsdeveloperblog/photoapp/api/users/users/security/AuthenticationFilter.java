package com.appsdeveloperblog.photoapp.api.users.users.security;

import com.appsdeveloperblog.photoapp.api.users.users.ui.model.LoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
}