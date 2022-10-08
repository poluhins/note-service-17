package com.example.demo.config;

import com.example.demo.db.repository.NoteRepository;
import com.example.demo.service.converter.NoteConverter;
import com.example.demo.service.converter.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public UserConverter userConverter(NoteRepository repository) {
        return new UserConverter(repository::getCountByAuthorId);
    }

    @Bean
    public NoteConverter noteConverter(UserConverter converter) {
        return new NoteConverter(converter::from);
    }

}
