package com.example.demo.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO implements Serializable {

    Integer id;
    String name;
    String avatarLink;
    int countOfNotes;

}
