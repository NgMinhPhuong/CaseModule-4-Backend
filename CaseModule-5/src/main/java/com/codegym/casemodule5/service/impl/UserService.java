package com.codegym.casemodule5.service.impl;

import com.codegym.casemodule5.dto.UserDto;
import com.codegym.casemodule5.model.Role;
import com.codegym.casemodule5.model.User;
import com.codegym.casemodule5.repository.IRoleRepository;
import com.codegym.casemodule5.repository.IUserRepository;
import com.codegym.casemodule5.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "users")
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private ModelMapper modelMapper =  new ModelMapper();


    @Override
    public boolean updateUserPass(String username, String newPass) {
       try {
           User user = findByUsername_v02(username);
           if (user != null){
               user.setPassword(passwordEncoder.encode(newPass));
               userRepository.save(user);
               return true;
           } else {
               return false;
           }
       } catch (Exception e) {
           System.out.println("Error during updating user: " + e.getMessage());
           return false;
       }
    }

    @Override
    public boolean checkUserExisted(String type, String username) {
        Optional<User> user = Optional.empty();
        switch (type.toUpperCase()) {
            case "USERNAME":
                user = userRepository.findByUsername(username);
                break;
            case "EMAIL":
                user = userRepository.findByEmail(username);
                break;
            case "PHONE":
                user = userRepository.findByPhone(username);
                break;
        }
        return user.isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
       @Cacheable(key = "'allUser'")
        public List<UserDto> findAll() {
            Type targetListType = new TypeToken<List<UserDto>>() {
            }.getType();
            return modelMapper.map(userRepository.findAll(), targetListType);
        }

        @Override
        @Cacheable(key = "#id")
        public Optional<UserDto> findById(Long id) {
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.map(user -> modelMapper.map(user, UserDto.class));
        }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<User> userOptional = userRepository.getUserByUsernameOrEmailOrPhone(username);
        return Optional.ofNullable(modelMapper.map(userOptional.get(), UserDto.class));
    }

    public User findByUsername_v02(String username) {
        return userRepository.getUserByUsernameOrEmailOrPhone(username).orElse(null);
    }

    @Override
    public boolean add(UserDto userDTO) {


        if (!(userRepository.findByUsernameOrEmailOrPhone(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPhone()).isPresent())) {
            User user = User.builder().username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .address(userDTO.getAddress())
                    .email(userDTO.getEmail())
                    .avatar(userDTO.getAvatar())
                    .fullName(userDTO.getFullName())
                    .role(roleRepository.findById(Long.valueOf(userDTO.getRoleId())).orElse(null))
                    .isActivated(userDTO.getIsActivated())
                    .phone(userDTO.getPhone()).build();
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    @CacheEvict(value ="users", key = "#id")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Scheduled(fixedRate = 50000)
    @CacheEvict(value = "users", allEntries = true)
    public void clearAllCache() {
        System.out.println("Clear all caches");
    }
}