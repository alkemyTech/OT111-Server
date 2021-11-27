package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql = "UPDATE User SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String photo;

    //Anotaciones correspondientes para la unión
    private Role roleId;

    private OffsetDateTime createdDate;

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    private Boolean deleted = Boolean.FALSE;
}
