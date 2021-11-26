package com.alkemy.ong.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationResponse {
    private UserDTO authenticatedUser;
}
