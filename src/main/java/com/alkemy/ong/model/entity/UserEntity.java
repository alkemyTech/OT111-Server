package com.alkemy.ong.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "usser")
@Data
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "El usuario debe ser un email")
    private String email;
    
    @Size(min = 2)
    private String password;



}
