package dev.kaiserInc.AngelOfTheDices.exception.types;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
