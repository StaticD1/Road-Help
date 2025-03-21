package ru.itis.roadhelp.exception;

public class BannedUserException extends RuntimeException {
    public BannedUserException(String message) {
        super(message);
    }
}
