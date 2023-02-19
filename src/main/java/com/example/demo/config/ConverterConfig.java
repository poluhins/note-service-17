package com.example.demo.config;

import com.example.demo.domain.entity.Note;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.NoteDTO;
import com.example.demo.domain.model.UserDTO;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.Converter;
import com.example.demo.service.impl.NoteConverter;
import com.example.demo.service.impl.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Bean
    public Converter<User, UserDTO> userConverter(NoteRepository noteRepository) {
        return new UserConverter(noteRepository::getCountByAuthorId);
    }

    @Bean
    public Converter<Note, NoteDTO> noteConverter(Converter<User, UserDTO> converter) {
        return new NoteConverter(converter::to, converter::from);
    }

}
