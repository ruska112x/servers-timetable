package org.karabalin.timetablebackend.core.exceptions;

public class EditInDatabaseException extends RuntimeException {
    public EditInDatabaseException() {
        super();
    }

    public EditInDatabaseException(String message) {
        super(message);
    }
}
