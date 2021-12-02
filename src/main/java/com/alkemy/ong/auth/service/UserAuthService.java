package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.dto.AuthenticationRequest;
import com.alkemy.ong.model.dto.UserDTO;

public interface UserAuthService {

    UserDTO loginAttempt(AuthenticationRequest authenticationRequest) throws Exception ;

}
