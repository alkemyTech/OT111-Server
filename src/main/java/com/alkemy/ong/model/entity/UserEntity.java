package com.alkemy.ong.model.entity;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usser")
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

=======
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usser")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;
>>>>>>> feature/segurity

}
