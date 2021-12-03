package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.model.response.security.RegisterResponse;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public UserEntity registerRequestDTO2Entity(RegisterRequest userDto){
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhoto(userDto.getPhoto());
        return newUser;
    }

    public RegisterResponse entity2RegisterResponseDTO(UserEntity userEntity){
        return RegisterResponse.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .photo(userEntity.getPhoto())
                .build();
    }

    public AuthenticationResponse entity2LoginResponseDTO(UserEntity userEntity){
        return AuthenticationResponse.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

}
