package com.alkemy.ong.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiExceptionResponse> noSuchElementExceptionHandler(NoSuchElementException noSuchElementException) {
        var apiException = new ApiExceptionResponse(
                noSuchElementException.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiExceptionResponse> authenticationExceptionHandler(AuthenticationException authenticationException) {
        var apiException = new ApiExceptionResponse(
                authenticationException.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiException);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiExceptionResponse> illegalArgumentExceptionHandler(IllegalArgumentException illegalArgumentException) {
        var apiException = new ApiExceptionResponse(
                illegalArgumentException.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
    }

    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ApiExceptionResponse> genericExceptionHandler(GenericException genericException) {
        var apiException = new ApiExceptionResponse(
                genericException.getMessage(),
                genericException.getHttpStatus().value()
        );
        return ResponseEntity.status(genericException.getHttpStatus()).body(apiException);
    }

    @ExceptionHandler(value = {EmailException.class})
    public ResponseEntity<ApiExceptionResponse> emailExceptionHandler(EmailException emailException) {
        var apiException = new ApiExceptionResponse(
                emailException.getMessage(),
                emailException.getHttpStatus().value()
        );
        return ResponseEntity.status(emailException.getHttpStatus()).body(apiException);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiConstraintViolationExceptionResponse> constraintViolationException(ConstraintViolationException constraintViolationException) {
        List<String> details = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            details.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        var apiConstraintViolationException = ApiConstraintViolationExceptionResponse.builder()
                .message("Constraint Violations")
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .errors(details)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiConstraintViolationException);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        List<FieldError> errorFields = ex.getBindingResult().getFieldErrors();

        for (FieldError e : errorFields) {
            details.add(e.getField() + " : " + e.getDefaultMessage());
        }
        var apiConstraintViolationException = ApiConstraintViolationExceptionResponse.builder()
                .message("Constraint Violations")
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .errors(details)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiConstraintViolationException);
    }
}
