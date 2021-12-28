package com.alkemy.ong.auth.service;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.UserResponse;

public interface UserAuthService {

    AuthenticationResponse loginAttempt(AuthenticationRequest authenticationRequest) throws GenericException;

    UserResponse meData(String authorization);
}
