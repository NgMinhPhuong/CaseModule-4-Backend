package com.codegym.casemodule5.service.impl;

import com.codegym.casemodule5.config.TokenProvider;
import com.codegym.casemodule5.payload.LoginRequest;
import com.codegym.casemodule5.payload.LoginResponse;
import com.codegym.casemodule5.service.ILoginService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate login info
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                    loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //Generate token
            String token = tokenProvider.generateToken(authentication);
            return new LoginResponse("Login successfully", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LoginResponse("Login failed", null);
    }
}