package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.user.UserDTO;

import java.io.UnsupportedEncodingException;

public interface UserAuthService {

    AuthenticationResponse loginAttempt(AuthenticationRequest authenticationRequest) throws Exception ;

    UserDTO meData(String authorization) throws UnsupportedEncodingException;
}
