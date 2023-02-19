package service;

import com.example.demo.domain.entity.Note;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.NoteDTO;
import com.example.demo.domain.model.UserDTO;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.Converter;
import com.example.demo.service.impl.NoteConverter;
import com.example.demo.service.impl.UserConverter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean("user-converter")
    public Converter<User, UserDTO> userConverter() {
        return new UserConverter((i) -> 0);
    }

    @Bean
    public Converter<Note, NoteDTO> noteConverter(Converter<User, UserDTO> converter) {
        return new NoteConverter(converter::to, converter::from);
    }

}
