package com.example.demo.service.impl;

import com.example.demo.repository.UserRepository;
import com.example.demo.domain.model.UserDTO;
import com.example.demo.service.Converter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository repository;
    UserConverter converter;

    public UserDTO newUser(UserDTO dto) {
        val user = converter.from(dto);
        val saved = repository.save(user);
        return converter.to(saved);
    }

    public UserDTO edit(int id, UserDTO dto) {
        val original = repository.findById(id)
                .orElse(null);
        if (original == null) {
            return null;
        }

        val user = converter.from(dto);
        val merged = Converter.merge(original, user);
        merged.setId(id);
        val result = repository.save(merged);
        return converter.to(result);
    }

}
