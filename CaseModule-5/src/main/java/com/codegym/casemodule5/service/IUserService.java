package com.codegym.casemodule5.service;

import com.codegym.casemodule5.dto.UserDto;
import com.codegym.casemodule5.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    Optional<UserDto> findByUsername(String username);
    boolean add(UserDto userDto);
    User findByUsername_v02(String username);
    void delete(Long id);
    boolean updateUserPass(String username, String newPass);
    boolean checkUserExisted(String type,String username);
    Optional<User> findByEmail(String email);
}
