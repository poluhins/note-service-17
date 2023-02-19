package com.example.demo.controller.impl;

import com.example.demo.domain.exception.NoteNotFoundException;
import com.example.demo.domain.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.ResponseEntity.notFound;

@ControllerAdvice
public class ErrorControllerHandler {

    @ExceptionHandler({UserNotFoundException.class, NoteNotFoundException.class})
    public ResponseEntity<?> handleUserNotFound() {
        return notFound().build();
    }

}
