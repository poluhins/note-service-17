package com.example.demo.service.converter;

import com.example.demo.db.entity.User;
import com.example.demo.model.UserDTO;
import com.example.demo.service.Converter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserConverter extends Converter<UserDTO, User> {

    Function<Integer, Integer> getCountOfNotes;

    @Override
    public User to(UserDTO dto) {
        val user = new User();
        if (dto == null) {
            return null;
        }
        BeanUtils.copyProperties(dto, user, "countOfNotes");
        return user;
    }

    @Override
    public UserDTO from(User user) {
        val dto = new UserDTO();
        if (user == null) {
            return null;
        }
        BeanUtils.copyProperties(user, dto);

        val countOfNotes = getCountOfNotes.apply(user.getId());
        dto.setCountOfNotes(countOfNotes);
        return dto;
    }

}
