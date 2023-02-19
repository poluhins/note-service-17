package service;

import com.example.demo.DemoApplication;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Converter;
import com.example.demo.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith({MockitoExtension.class})
@ContextConfiguration(classes = TestConfig.class)
public class UserServiceTest {

    @Mock
    UserRepository repository;

    @Autowired
    @Qualifier("user-converter")
    Converter<User, UserDTO> converter;

    UserService userService;

    @BeforeEach
    public void setUp() {

        userService = new UserService(repository, converter);
    }

    @Test
    public void userExistsTest() {

        assertFalse(userService.exists(1));

        when(repository.existsById(1))
                .thenReturn(true);

        assertTrue(userService.exists(1));

    }

    @Test
    public void newUserTest() {

        val user = UserDTO.builder()
                .name("SSS")
                .build();
        val entity = new User();
        entity.setName(user.getName());


        val resulted = userService.newUser(user);

        log.info("Original {}, resulted {}", user.getName(), resulted.getName());
        assertNotEquals(user.getName(), resulted.getName());
    }

}
