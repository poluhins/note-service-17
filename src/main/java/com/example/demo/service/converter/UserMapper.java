package com.example.demo.service.converter;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO user);

}
