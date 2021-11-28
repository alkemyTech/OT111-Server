package com.alkemy.ong.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;


@Data
public class UserDto {

    @Email(message = "Username must be an email")
    private String username;

    private String password;

}
