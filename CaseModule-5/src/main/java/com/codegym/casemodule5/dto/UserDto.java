package com.codegym.casemodule5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private Boolean isActivated;
}
