package com.alkemy.ong.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiException  {
    private  String message;
    private  int httpStatus;
    private  ZonedDateTime timestamp;
}
