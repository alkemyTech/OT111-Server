package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.request.RegisterRequest;
import com.alkemy.ong.model.response.RegisterResponse;
import com.alkemy.ong.model.response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {



    public UserEntity userDTO2Entity(RegisterRequest userDto){
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhoto(userDto.getPhoto());
        return newUser;
    }

    public RegisterResponse entity2ResponseDTO(UserEntity userEntity){
        return RegisterResponse.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .photo(userEntity.getPhoto())
                .build();
    }

    public AuthenticationResponse entity2ResponseLoginDTO(UserEntity userEntity){
        return AuthenticationResponse.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

}
