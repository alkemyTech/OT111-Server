package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.model.response.security.RegisterResponse;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public UserEntity registerRequestDTO2Entity(RegisterRequest userDto){
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setPassword(userDto.getPassword());
        newUser.setEmail(userDto.getEmail());
        newUser.setPhoto(userDto.getPhoto());
        return newUser;
    }

    public RegisterResponse entity2RegisterResponseDTO(UserEntity userEntity){
        return RegisterResponse.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();
    }

    public AuthenticationResponse userDetailsAndJwt2LoginResponseDTO(UserDetails userInContext, String jwt){
        return AuthenticationResponse.builder()
                .email(userInContext.getUsername())
                .password(userInContext.getPassword())
                .jwt(jwt)
                .build();
    }

}
