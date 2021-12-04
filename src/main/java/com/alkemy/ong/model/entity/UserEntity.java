package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Data
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class UserEntity  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String photo;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "usersid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "rolesid", referencedColumnName = "id")
                    )
    private Collection<RoleEntity> role;

    // private boolean tokenExpired;


    private OffsetDateTime createdDate = OffsetDateTime.now();

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    private Boolean deleted = Boolean.FALSE;
}
