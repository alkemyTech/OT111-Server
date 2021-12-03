package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.response.UserResponseLoginDTO;

public interface UserAuthService {

    UserResponseLoginDTO loginAttempt(AuthenticationRequest authenticationRequest) throws Exception ;

}
