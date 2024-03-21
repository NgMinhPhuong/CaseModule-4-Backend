package com.codegym.casemodule5.service.impl;

import com.codegym.casemodule5.dto.RegisterDto;
import com.codegym.casemodule5.dto.RoleDto;
import com.codegym.casemodule5.dto.RoleType;
import com.codegym.casemodule5.dto.StatusType;
import com.codegym.casemodule5.dto.UserDto;
import com.codegym.casemodule5.model.Role;
import com.codegym.casemodule5.model.User;
import com.codegym.casemodule5.payload.register.RegisterRequest;
import com.codegym.casemodule5.payload.register.RegisterResponse;
import com.codegym.casemodule5.repository.IRoleRepository;
import com.codegym.casemodule5.repository.IUserRepository;
import com.codegym.casemodule5.service.IRegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService implements IRegisterService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        UserDto userDto = registerRequest.getRegisterDto().getUserDto();
        if (!isUserExisted(userDto)){
            return new RegisterResponse("Register failed!",null);
        } else {
            User newUser = modelMapper.map(userDto, User.class);
            newUser.setIsActivated(StatusType.IS_ACTIVATED.getIsActivated());
            String password = bCryptPasswordEncoder.encode(newUser.getPassword());
            newUser.setPassword(password);

            Role userRole = roleRepository.findById(RoleType.ROLE_USER.getId()).orElse(null);
            newUser.setRole(userRole);

            User userCreated = userRepository.save(newUser);
            UserDto userDtoCreated = modelMapper.map(userCreated, UserDto.class);
            RoleDto roleDtoCreated = modelMapper.map(userRole, RoleDto.class);

            RegisterDto registerDto = new RegisterDto(userDtoCreated,roleDtoCreated);
            return new RegisterResponse("Register successfully!",registerDto);
        }
    }

    private Boolean isUserExisted(UserDto newUserDto){
        User currentUser = userRepository.findByUsername(newUserDto.getUsername()).orElse(null);
        return Optional.ofNullable(currentUser).isEmpty();
    }
}
