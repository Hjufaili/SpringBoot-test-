package com.myFullstackAlharith.employee_management.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionResponse {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GlobleResponse<?>> handleNoResourceFoundException(NoResourceFoundException ex) {

        var errors = List.of(
                new GlobleResponse.ErrorItem("Resource is not found"));
        return new ResponseEntity<>(new GlobleResponse<>(errors), HttpStatus.NOT_FOUND);
    }
}


