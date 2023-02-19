package com.example.demo.controller;


import com.example.demo.domain.exception.NoteNotFoundException;
import com.example.demo.domain.exception.UserNotFoundException;
import com.example.demo.domain.model.NoteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Note controller",
        description = "Service for making notes")
@RequestMapping("/note")
public interface NoteController {

    @Operation(summary = "New notes creating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New note successful created",
            content = {
                    @Content(schema = @Schema(implementation = NoteDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = {})
    })
    @PostMapping
    ResponseEntity<NoteDTO> createNewNote(@RequestBody NoteDTO note) throws UserNotFoundException;

    @Operation(summary = "New notes creating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New note successful created",
                    content = {
                            @Content(schema = @Schema(implementation = NoteDTO.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = {
                            @Content(schema = @Schema(implementation = UserNotFoundException.class))
                    })
    })
    @GetMapping("/{id}")
    ResponseEntity<NoteDTO> getNoteById(@PathVariable int id) throws NoteNotFoundException;

}
