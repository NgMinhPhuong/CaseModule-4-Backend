package com.codegym.casemodule5.controller;

import com.codegym.casemodule5.payload.login.LoginRequest;
import com.codegym.casemodule5.payload.login.LoginResponse;
import com.codegym.casemodule5.payload.register.RegisterRequest;
import com.codegym.casemodule5.payload.register.RegisterResponse;
import com.codegym.casemodule5.service.impl.LoginService;
//import com.codegym.casemodule5.service.impl.RegisterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private LoginService loginService;
//    @Autowired
//    private RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        System.out.println(loginRequest);
        if (Optional.ofNullable(loginResponse.getToken()).isPresent()){
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(loginResponse,HttpStatus.BAD_REQUEST);
    }


//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
//        RegisterResponse registerResponse = registerService.register(registerRequest);
//
//        if (Optional.ofNullable(registerResponse.getData()).isPresent()){
//            return new ResponseEntity<>(registerResponse,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(registerResponse,HttpStatus.BAD_REQUEST);
//    }
@PostMapping("/logout")
public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
}


}
