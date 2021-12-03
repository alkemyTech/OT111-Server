package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.dto.UserDTO;
import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity userDTO2Entity(UserDTO userDto){
        //Traigo mediate userDTO (de entity) los siguientes atributos:
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());

        return newUser;
    }

    public UserDTO entity2DTO(UserEntity userEntity){
        //ver builder, patron de diseño, para que lo utilizamos.
        //Con el builder, "ahorro" en realizar los sets de los atributos a la hora de devolver.
        //Lo coloco en return directamente.
        return  UserDTO.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

}
