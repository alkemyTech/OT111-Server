package com.alkemy.ong.exception;

import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class ApiException  {
    private  String message;
    private  int httpStatus;
    private  ZonedDateTime timestamp;
    private List errors;

    public ApiException(String message, int httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ApiException(String message, int httpStatus, ZonedDateTime timestamp, List errors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.errors = errors;
    }
}
