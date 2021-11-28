package com.alkemy.ong.auth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {

    @Email(message = "El usuario debe ser un email")
    private String username;


    @Size(min = 2)
    private String password;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Integer id;


}
