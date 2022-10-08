package com.example.demo.controller;

import com.example.demo.model.NoteDTO;
import com.example.demo.service.impl.NoteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteController {

    NoteService noteService;

    @PostMapping("/")
    public ResponseEntity<NoteDTO> createNewNote(@RequestHeader("UserId") int author,
                                                 @RequestBody NoteDTO dto) {
        return ok(noteService.newNote(author, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> editNote(@RequestHeader("UserId") int author,
                                            @PathVariable int id,
                                            @RequestBody NoteDTO dto) {
        if (!noteService.isAuthor(id, author)) {
            return status(HttpStatus.FORBIDDEN).build();
        }

        val result = noteService.edit(id, author, dto);
        if (result == null) {
            return notFound().build();
        }

        return ok(result);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Collection<NoteDTO>> getNotesByAuthor(@PathVariable int authorId) {
        return ok(noteService.getByAuthorId(authorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@RequestHeader("UserId") int author,
                                           @PathVariable int id) {
        if (!noteService.deleteNote(author, id)) {
            return status(HttpStatus.FORBIDDEN).build();
        }

        return ok().build();
    }


}
