package com.alkemy.ong.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {

    private String email;
    private String password;
    private String jwt;
}
