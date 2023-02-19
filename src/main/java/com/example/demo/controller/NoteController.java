package com.example.demo.controller;


import com.example.demo.domain.exception.NoteNotFoundException;
import com.example.demo.domain.exception.UserNotFoundException;
import com.example.demo.domain.model.NoteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/note")
public interface NoteController {

    @PostMapping
    ResponseEntity<NoteDTO> createNewNote(@RequestBody NoteDTO note) throws UserNotFoundException;

    @GetMapping("/{id}")
    ResponseEntity<NoteDTO> getNoteById(@PathVariable int id) throws NoteNotFoundException;

}
