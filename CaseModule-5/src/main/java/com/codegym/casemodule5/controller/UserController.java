package com.codegym.casemodule5.controller;

import com.codegym.casemodule5.dto.UserDto;
import com.codegym.casemodule5.model.User;
import com.codegym.casemodule5.repository.IUserRepository;
import com.codegym.casemodule5.service.IUserService;
import com.codegym.casemodule5.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.findAll();
    }
    /* ---------------- GET USER BY ID ------------------------ */
    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public @ResponseBody UserDto getUserById(@PathVariable Long id) {
        Optional<UserDto> user = userService.findById(id);
        return user.orElse(null);
    }
    /* ---------------- CREATE NEW USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        if (userService.add(user)) {
            return new ResponseEntity<>("Created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User Existed!", HttpStatus.BAD_REQUEST);
        }
    }
    /* ---------------- DELETE USER ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
}
