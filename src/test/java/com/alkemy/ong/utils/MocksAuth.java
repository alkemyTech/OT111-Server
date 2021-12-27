package com.alkemy.ong.utils;

import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.request.security.RegisterRequest;

public class MocksAuth {

    public static AuthenticationRequest buildAuthRequest() {
        return AuthenticationRequest.builder()
                .username("admin1@email.com")
                .password("password")
                .build();
    }

    public static AuthenticationRequest buildAuthRequestInvalid() {
        return AuthenticationRequest.builder()
                .username("admin1@email.com")
                .password("password2")
                .build();
    }

    public static RegisterRequest buildRegisterRequest() {
        return RegisterRequest.builder()
                .firstName("Mock user")
                .lastName("Mock lastname")
                .email("mock@email.com")
                .password("password")
                .photo("photo.jpg")
                .roleId(2)
                .build();
    }

}
