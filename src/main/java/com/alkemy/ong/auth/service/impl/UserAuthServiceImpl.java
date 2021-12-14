package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.service.JwtUtil;
import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.mapper.AuthenticationMapper;
import com.alkemy.ong.model.mapper.RoleMapper;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationMapper authenticationMapper;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleMapper roleMapper;

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
    public UserDTO meData(String userMail){

        Optional<UserEntity> user = userRepository.findByEmail(userMail);

        return UserDTO.builder()
                .id(user.get().getId())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .photo(user.get().getPhoto())
                .roles(roleMapper.entity2DTO(user.get().getRoles()))
                .createdDate(user.get().getCreatedDate())
                .createdBy(user.get().getCreatedBy())
                .modifiedDate(user.get().getModifiedDate())
                .modifiedBy(user.get().getModifiedBy())
                .build();
    }


}
