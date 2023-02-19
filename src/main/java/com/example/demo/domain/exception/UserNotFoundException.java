package com.example.demo.domain.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(Integer id) {
        super("Cannot find user by id = " + id);
    }
}
