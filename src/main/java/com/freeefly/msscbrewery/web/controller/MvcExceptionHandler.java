package com.freeefly.msscbrewery.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MvcExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity validationErrorHandler(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage())
                .collect(Collectors.toList());

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity bindingErrorHandler(BindException e) {
        return new ResponseEntity(e.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
