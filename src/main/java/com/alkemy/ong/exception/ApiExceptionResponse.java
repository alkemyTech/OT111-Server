package com.alkemy.ong.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ApiExceptionResponse {

    private String message;
    private int httpStatus;
    private ZonedDateTime timestamp = ZonedDateTime.now();

    public ApiExceptionResponse(String message, int httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
