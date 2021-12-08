package com.alkemy.ong.model.response.security;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {

    private String email;
    private String jwt;
}
