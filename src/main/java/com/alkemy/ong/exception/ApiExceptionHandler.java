package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ApiException> noSuchElementExceptionHandler(NoSuchElementException noSuchElementException) {
        var apiException = new ApiException(
                noSuchElementException.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ApiException> authenticationExceptionHandler(AuthenticationException authenticationException) {
        var apiException = new ApiException(
                authenticationException.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiException);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiException> illegalArgumentExceptionHandler(IllegalArgumentException illegalArgumentException) {
        var apiException = new ApiException(
                illegalArgumentException.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
    }

    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ApiException> genericExceptionHandler(GenericException genericException) {
        var apiException = new ApiException(
                genericException.getMessage(),
                genericException.getHttpStatus().value()
        );
        return ResponseEntity.status(genericException.getHttpStatus()).body(apiException);
    }

    @ExceptionHandler(value = {EmailException.class})
    public ResponseEntity<ApiException> emailExceptionHandler(EmailException emailException) {
        var apiException = new ApiException(
                emailException.getMessage(),
                emailException.getHttpStatus().value()
        );
        return ResponseEntity.status(emailException.getHttpStatus()).body(apiException);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiConstraintViolationException> constraintViolationException(ConstraintViolationException constraintViolationException) {
        List<String> details = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            details.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        var apiConstraintViolationException = ApiConstraintViolationException.builder()
                .message("Constraint Violations")
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .errors(details)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiConstraintViolationException);
    }

}
