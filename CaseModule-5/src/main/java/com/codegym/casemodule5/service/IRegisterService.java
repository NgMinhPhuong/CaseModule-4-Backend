package com.codegym.casemodule5.service;

import com.codegym.casemodule5.payload.register.RegisterRequest;
import com.codegym.casemodule5.payload.register.RegisterResponse;

public interface IRegisterService {
    RegisterResponse register(RegisterRequest request);
}
