package com.noname.userapi.controller;

import com.noname.userapi.exception.UserAlreadyExistsException;
import com.noname.userapi.exception.UserNotFoundException;
import io.swagger.api.UsersApiController;
import io.swagger.model.UserError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = UsersApiController.class)
public class UserApiExceptionHandler {

    @ExceptionHandler(value = { UserAlreadyExistsException.class})
    protected ResponseEntity<UserError> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(new UserError().message(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { UserNotFoundException.class})
    protected ResponseEntity<UserError> handleUserNotFoundException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(new UserError().message(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class})
    protected ResponseEntity<UserError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new UserError().message(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}