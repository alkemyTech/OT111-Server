package com.alkemy.ong.exception;

<<<<<<< Updated upstream
=======
import lombok.Builder;
>>>>>>> Stashed changes
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiConstraintViolationException {

    private String message;
    private int httpStatus;
    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private List<String> errors;
}
