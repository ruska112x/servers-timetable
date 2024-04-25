package org.karabalin.timetablebackend.core.handlers;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<String> incorrectResultSizeDataAccessHandler(IncorrectResultSizeDataAccessException e) {
        return ResponseEntity.notFound().build();
    }
}
