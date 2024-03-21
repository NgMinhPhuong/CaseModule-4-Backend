package com.codegym.casemodule5.payload.register;

import com.codegym.casemodule5.dto.RegisterDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private RegisterDto registerDto;
}
