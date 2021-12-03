package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
<<<<<<< HEAD
@Table(name= "roles")
@SQLDelete(sql = "UPDATE roles SET deleted = true WHERE id=?")
=======
@Entity
@Table(name= "role")
@SQLDelete(sql = "UPDATE role SET deleted = true WHERE id=?")
>>>>>>> e69f8e1f8d0766df6e81b886d724a3675cfcc946
@Where(clause = "deleted=false")
@Entity
public class RoleEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    private OffsetDateTime createdDate;

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    private boolean deleted = Boolean.FALSE;
}
