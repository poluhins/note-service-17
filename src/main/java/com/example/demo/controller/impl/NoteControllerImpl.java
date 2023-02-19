package com.example.demo.controller.impl;

import com.example.demo.controller.NoteController;
import com.example.demo.domain.exception.NoteNotFoundException;
import com.example.demo.domain.exception.UserNotFoundException;
import com.example.demo.domain.model.NoteDTO;
import com.example.demo.service.impl.NoteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteControllerImpl implements NoteController {

    NoteService noteService;

    @Override
    public ResponseEntity<NoteDTO> createNewNote(NoteDTO note) throws UserNotFoundException {
        return ok(noteService.newNote(note));
    }

    @Override
    public ResponseEntity<NoteDTO> getNoteById(int id) throws NoteNotFoundException {
        return ok(noteService.findById(id));
    }
}
