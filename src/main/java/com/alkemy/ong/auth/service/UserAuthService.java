package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;

public interface UserAuthService {

    AuthenticationResponse loginAttempt(AuthenticationRequest authenticationRequest) throws Exception ;

}
