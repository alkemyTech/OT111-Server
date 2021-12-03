package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.request.UserRequestDTO;
import com.alkemy.ong.model.response.UserResponseDTO;
import com.alkemy.ong.model.response.UserResponseLoginDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {



    public UserEntity userDTO2Entity(UserRequestDTO userDto){
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhoto(userDto.getPhoto());
        return newUser;
    }

    public UserResponseDTO entity2ResponseDTO(UserEntity userEntity){
        return UserResponseDTO.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .photo(userEntity.getPhoto())
                .build();
    }

    public UserResponseLoginDTO entity2ResponseLoginDTO(UserEntity userEntity){
        return UserResponseLoginDTO.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

}
