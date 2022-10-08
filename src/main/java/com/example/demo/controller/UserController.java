package com.example.demo.controller;

import com.example.demo.model.UserDTO;
import com.example.demo.service.impl.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService service;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO dto) {
        return ok(service.newUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> editUser(@PathVariable int id,
                                            @RequestBody UserDTO edited) {
        val resulted = service.edit(id, edited);
        if (resulted == null) {
            return notFound().build();
        }

        return ok(resulted);
    }

}
