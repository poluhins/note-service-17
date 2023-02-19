package com.example.demo.service.impl;

import com.example.demo.domain.entity.Note;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.NoteDTO;
import com.example.demo.domain.model.UserDTO;
import com.example.demo.service.Converter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.function.Function;

import static java.time.ZoneOffset.UTC;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteConverter extends Converter<Note, NoteDTO> {

    Function<User, UserDTO> userFromConverter;
    Function<UserDTO, User> userToConverter;

    @Override
    public NoteDTO to(Note note) {

        if (note == null) {
            return null;
        }

        return NoteDTO.builder()
                .id(note.getId())
                .text(note.getText())
                .author(userFromConverter.apply(note.getAuthor()))
                .createdDateTime(fromEpoch(note.getCreatedDateTime()))
                .editedDateTime(fromEpoch(note.getEditedDateTime()))
                .build();
    }

    @Override
    public Note from(NoteDTO noteDTO) {
        if (noteDTO == null) {
            return null;
        }

        val result = new Note();
        result.setText(noteDTO.getText());
        result.setCreatedDateTime(fromLDT(noteDTO.getCreatedDateTime(), true));
        result.setEditedDateTime(fromLDT(noteDTO.getEditedDateTime(), false));
        result.setAuthor(userToConverter.apply(noteDTO.getAuthor()));
        return result;
    }

    private Long fromLDT(LocalDateTime time, boolean created) {
        return ofNullable(time)
                .map(t -> t.toEpochSecond(UTC))
                .orElse(created ? Instant.now().getEpochSecond() : null);
    }

    private LocalDateTime fromEpoch(Long seconds) {
        return ofNullable(seconds)
                .map(s -> LocalDateTime.ofEpochSecond(s, 0, UTC))
                .orElse(null);
    }
}
