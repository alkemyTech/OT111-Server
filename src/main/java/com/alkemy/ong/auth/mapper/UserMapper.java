package com.alkemy.ong.auth.mapper;

import com.alkemy.ong.auth.dto.UserDto;
import com.alkemy.ong.auth.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordencoder;

    public UserEntity UserDTO2Entity(UserDto userDto){

        UserEntity newUser = new UserEntity();

        newUser.setUsername(userDto.getUsername());

        newUser.setPassword(passwordencoder.encode(userDto.getPassword()));

        return newUser;
    }

    public UserDto entity2DTO(UserEntity userEntity){

        UserDto userDto = new UserDto();

        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());

        return userDto;
    }

}
