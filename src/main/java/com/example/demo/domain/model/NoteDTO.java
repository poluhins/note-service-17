package com.example.demo.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteDTO implements Serializable {

    Integer id;
    String text;
    UserDTO author;
    LocalDateTime createdDateTime;
    LocalDateTime editedDateTime;

}
