package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ApiException> noSuchElementExceptionHandler(NoSuchElementException noSuchElementException){
       var apiException = new ApiException(
               noSuchElementException.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ApiException> authenticationExceptionHandler(AuthenticationException authenticationException){
        var apiException = new ApiException(
                authenticationException.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiException);
    }

    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<ApiException> genericExceptionHandler(GenericException genericException){
        var apiException = new ApiException(
                genericException.getMessage(),
                genericException.getHttpStatus().value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(genericException.getHttpStatus()).body(apiException);
    }

    @ExceptionHandler(value = {EmailException.class})
    public ResponseEntity<ApiException> emailExceptionHandler(EmailException emailException){
        var apiException = new ApiException(
                emailException.getMessage(),
                emailException.getHttpStatus().value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(emailException.getHttpStatus()).body(apiException);
    }

    @ExceptionHandler(value = {javax.validation.ConstraintViolationException.class})
    public ResponseEntity<ApiConstraintViolationException> constraintVioltaionException(javax.validation.ConstraintViolationException constraintViolationException){
        List<String> details = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            details.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        var apiConstraintVioltaionException = new ApiConstraintViolationException(
                "Constraint Violations",
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(ZoneId.of("Z")),
                details
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiConstraintVioltaionException);
    }

}
