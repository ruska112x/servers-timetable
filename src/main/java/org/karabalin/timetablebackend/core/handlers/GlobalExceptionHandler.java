package org.karabalin.timetablebackend.core.handlers;

import org.karabalin.timetablebackend.core.exceptions.AddInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.EditInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.NotFoundInDatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> {
            stringBuilder.append(((FieldError) error).getField()).append(": ").append(error.getDefaultMessage()).append("\n");
        });
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundInDatabaseException.class)
    public ResponseEntity<String> notFoundInDatabaseExceptionHandler(NotFoundInDatabaseException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddInDatabaseException.class)
    public ResponseEntity<String> addInDatabaseExceptionHandler(AddInDatabaseException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EditInDatabaseException.class)
    public ResponseEntity<String> editInDatabaseExceptionHandler(EditInDatabaseException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
