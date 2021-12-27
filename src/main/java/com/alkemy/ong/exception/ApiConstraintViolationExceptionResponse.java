package com.alkemy.ong.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiConstraintViolationExceptionResponse {

    private String message;
    private int httpStatus;
    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private List<String> errors;
}
