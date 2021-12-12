package com.alkemy.ong.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class ApiConstraintViolationException {
    private  String message;
    private  int httpStatus;
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private List<String> errors;

    public ApiConstraintViolationException(String message, int httpStatus, List<String> errors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errors = errors;
    }
}
