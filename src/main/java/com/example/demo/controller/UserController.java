package com.example.demo.controller;

import com.example.demo.domain.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserController {

    @PostMapping
    ResponseEntity<UserDTO> register(@RequestBody UserDTO user);

}
