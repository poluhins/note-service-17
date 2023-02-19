package com.example.demo.domain.exception;

public class NoteNotFoundException extends Exception {

    public NoteNotFoundException(Integer id) {
        super("Note with id " + id + " not found ");
    }
}
