package com.alkemy.ong.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class GenericException extends RuntimeException {
    private  String message;
    private HttpStatus httpStatus;
}
