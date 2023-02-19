package com.example.demo.controller.impl;

import com.example.demo.controller.UserController;
import com.example.demo.domain.model.UserDTO;
import com.example.demo.service.impl.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserControllerImpl implements UserController {

    UserService service;

    @Override
    public ResponseEntity<UserDTO> register(UserDTO user) {
        return ok(service.newUser(user));
    }
}
