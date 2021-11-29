package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.dto.AuthenticationRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthService {

    UserDetails loginAttempt(AuthenticationRequest authenticationRequest) throws Exception;

}
