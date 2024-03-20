package com.codegym.casemodule5.service;

import com.codegym.casemodule5.payload.LoginResponse;
import com.codegym.casemodule5.payload.LoginRequest;
public interface ILoginService {
    LoginResponse login(LoginRequest loginRequest);
}
