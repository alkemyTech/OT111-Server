package com.alkemy.ong.model.request.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Builder
public class AuthenticationRequest {

    private String username;
    private String password;

}
