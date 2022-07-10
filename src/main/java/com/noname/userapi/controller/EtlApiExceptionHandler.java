package com.noname.userapi.controller;

import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.swagger.api.EtlApiController;
import io.swagger.model.UserError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = EtlApiController.class)
public class EtlApiExceptionHandler {

    @ExceptionHandler(value = { CsvRequiredFieldEmptyException.class})
    protected ResponseEntity<UserError> handleCsvRequiredFieldEmptyException(CsvRequiredFieldEmptyException ex) {
        return new ResponseEntity<>(new UserError().message(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}