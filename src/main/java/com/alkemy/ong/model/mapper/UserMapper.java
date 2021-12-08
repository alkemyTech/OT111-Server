package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.UserEntity;
<<<<<<< HEAD
import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.response.user.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class UserMapper extends AbstractMapper<UserEntity, UserResponseDTO> {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserResponseDTO entity2DTO(UserEntity entity) {
        if (entity == null) return null;
        return UserResponseDTO.builder()
=======
import com.alkemy.ong.model.response.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class UserMapper extends AbstractMapper<UserEntity, UserDTO> {

    private final RoleMapper roleMapper;

    @Override
    public UserDTO entity2DTO(UserEntity entity) {
        if (entity == null) return null;
        return UserDTO.builder()
>>>>>>> develop
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
<<<<<<< HEAD
                .role(roleMapper.entity2DTO(entity.getRole()))
                .createdDate(entity.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)) //Check if this output format is convenient.
=======
                .roles(roleMapper.entity2DTO(entity.getRoles()))
                .createdDate(entity.getCreatedDate())
>>>>>>> develop
                .build();

    }

<<<<<<< HEAD
    public UserUpdateDTO entityUpdate2DTO(UserEntity entity) {
        if (entity == null) return null;
        return UserUpdateDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
//                .password(entity.getPassword())//TODO Enable when merge security branch, and to show pass, add decoder
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .role(roleMapper.entity2DTO(entity.getRole()))
                .build();

    }

    public UserEntity dto2Entity(UserUpdateDTO dto) {
        return null;
    }

    @Override
    public UserEntity dto2Entity(UserResponseDTO dto) {
        return null;
    }



=======
    @Override
    public UserEntity dto2Entity(UserDTO dto) {
        return null;
    }
>>>>>>> develop
}
