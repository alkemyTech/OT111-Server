package com.alkemy.ong.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class ContactRequestDTO {

    @NotEmpty(message = "Name is mandatory")
    private String name;

    private String phone;

    @NotEmpty(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Message is mandatory")
    private String message;
}
