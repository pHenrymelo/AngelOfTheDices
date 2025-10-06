package dev.kaiserInc.AngelOfTheDices.exception.types;

public class ForbiddenAccessException extends RuntimeException{
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
