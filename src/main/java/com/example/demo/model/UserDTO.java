package com.example.demo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO implements Serializable {

    int id;
    String name;
    String avatarLink;
    int countOfNotes;

}
