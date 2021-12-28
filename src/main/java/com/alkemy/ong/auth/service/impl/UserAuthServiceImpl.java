package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.service.JwtUtil;
import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.mapper.AuthenticationMapper;
import com.alkemy.ong.model.mapper.RoleMapper;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final JwtUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    private final AuthenticationMapper authenticationMapper;

    private final UserDetailsCustomService userDetailsCustomService;

    private final UserRepository userRepository;

    private final RoleMapper roleMapper;

    @Override
    public AuthenticationResponse loginAttempt(AuthenticationRequest authenticationRequest) {
        UserDetails userDetails = userDetailsCustomService.loadUserByUsername(authenticationRequest.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword(),
                userDetails.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        // Build Response:
        String jwt = jwtTokenUtil.generateToken(userDetails);
        return authenticationMapper.userDetailsAndJwt2LoginResponseDTO(userDetails, jwt);
    }

    @Override
    public UserResponse meData(String userMail){

        Optional<UserEntity> user = userRepository.findByEmail(userMail);

        return UserResponse.builder()
                .id(user.get().getId())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .photo(user.get().getPhoto())
                .roles(roleMapper.entity2DTO(user.get().getRoles()))
                .build();
    }


}
