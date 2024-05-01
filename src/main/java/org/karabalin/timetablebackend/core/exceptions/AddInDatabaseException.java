package org.karabalin.timetablebackend.core.exceptions;

public class AddInDatabaseException extends RuntimeException {
    public AddInDatabaseException() {
        super();
    }

    public AddInDatabaseException(String message) {
        super(message);
    }
}
