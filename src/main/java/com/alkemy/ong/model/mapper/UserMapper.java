package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.dto.UserDTO;
import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {



    public UserEntity userDTO2Entity(UserDTO userDto){
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        return newUser;
    }

    public UserDTO entity2DTO(UserEntity userEntity){
        //ver builder, patron de diseño, para que lo utilizamos.
        return  UserDTO.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

}
