package com.codegym.casemodule5.service;

import com.codegym.casemodule5.payload.login.LoginResponse;
import com.codegym.casemodule5.payload.login.LoginRequest;
public interface ILoginService {
    LoginResponse login(LoginRequest loginRequest);
}
