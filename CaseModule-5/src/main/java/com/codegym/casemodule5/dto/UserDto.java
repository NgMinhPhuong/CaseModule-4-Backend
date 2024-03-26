package com.codegym.casemodule5.dto;

import com.codegym.casemodule5.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private Set<Role> roles;
    private String phone;
    private String avatar;
    private Boolean isActivated;
}
