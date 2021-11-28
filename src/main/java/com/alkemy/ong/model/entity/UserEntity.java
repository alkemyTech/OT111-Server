package com.alkemy.ong.model.entity;

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

}
