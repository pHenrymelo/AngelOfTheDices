package dev.kaiserInc.AngelOfTheDices.exception.types;

public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(String message) {
        super(message);
    }
}
