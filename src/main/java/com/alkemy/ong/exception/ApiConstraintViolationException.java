package com.alkemy.ong.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiConstraintViolationException {
    private  String message;
    private  int httpStatus;
    private ZonedDateTime timestamp;
    private List errors;
}
