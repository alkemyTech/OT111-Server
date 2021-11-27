package com.alkemy.ong.auth.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private UserDTO authenticatedUser;
}
