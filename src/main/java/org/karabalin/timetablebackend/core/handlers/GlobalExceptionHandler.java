package org.karabalin.timetablebackend.core.handlers;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<String> incorrectResultSizeDataAccessHandler(IncorrectResultSizeDataAccessException e) {
        return ResponseEntity.ofNullable(e.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> dataAccessExceptionHandler(DataAccessException e) {
        return ResponseEntity.ofNullable(e.getMessage());
    }
}
