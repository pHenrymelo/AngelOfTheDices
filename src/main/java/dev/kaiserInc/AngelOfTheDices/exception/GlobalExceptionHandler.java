package dev.kaiserInc.AngelOfTheDices.exception;

import dev.kaiserInc.AngelOfTheDices.exception.dto.ErrorResponseDTO;
import dev.kaiserInc.AngelOfTheDices.exception.types.BusinessRuleException;
import dev.kaiserInc.AngelOfTheDices.exception.types.DataConflictException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessRule(BusinessRuleException ex, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Business Rule Violation", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", "Invalid Credentials", request.getRequestURI());
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataConflict(DataConflictException ex, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.CONFLICT, "Data Conflict", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity<ErrorResponseDTO> handleForbiddenAccess(ForbiddenAccessException ex, HttpServletRequest request) {
        return createErrorResponse(HttpStatus.FORBIDDEN, "Access Denied", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error", errors, request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", "An unexpected internal server error has occurred. Please contact support.", request.getRequestURI());
    }

    private ResponseEntity<ErrorResponseDTO> createErrorResponse(HttpStatus status, String error, String message, String path) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                Instant.now(),
                status.value(),
                error,
                message,
                path
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
