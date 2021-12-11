package com.alkemy.ong.exception;

import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ApiException  {
    private  String message;
    private  int httpStatus;
    private  ZonedDateTime timestamp;

    public ApiException(String message, int httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
