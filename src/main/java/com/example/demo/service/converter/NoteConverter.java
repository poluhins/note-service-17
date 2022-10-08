package com.example.demo.service.converter;

import com.example.demo.db.entity.Note;
import com.example.demo.db.entity.User;
import com.example.demo.model.NoteDTO;
import com.example.demo.model.UserDTO;
import com.example.demo.service.Converter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.function.Function;

import static java.time.ZoneOffset.UTC;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteConverter extends Converter<Note, NoteDTO> {

    Function<User, UserDTO> userFromConverter;

    @Override
    public NoteDTO to(Note note) {
        if (note == null) {
            return null;
        }

        val dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setText(note.getText());

        val author = userFromConverter.apply(note.getAuthor());
        dto.setAuthor(author);

        val created = LocalDateTime.ofEpochSecond(note.getCreatedDateTime(), 0, UTC);
        dto.setCreatedDateTime(created);

        if (note.getEditedDateTime() != null) {
            val edited = LocalDateTime.ofEpochSecond(note.getEditedDateTime(), 0, UTC);
            dto.setEditedDateTime(edited);
        }

        return dto;
    }

    @Override
    public Note from(NoteDTO noteDTO) {

        if (noteDTO == null) {
            return null;
        }

        val note = new Note();
        note.setText(noteDTO.getText());

        val created = noteDTO.getCreatedDateTime() != null ?
                noteDTO.getCreatedDateTime().toEpochSecond(UTC) :
                Instant.now().getEpochSecond();
        note.setCreatedDateTime(created);
        note.setEditedDateTime(noteDTO.getEditedDateTime() != null ?
                noteDTO.getEditedDateTime().toEpochSecond(UTC) :
                null);

        return note;
    }
}
