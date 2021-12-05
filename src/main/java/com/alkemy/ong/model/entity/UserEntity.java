package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor //Added annotation while working on OT111-35
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Builder //Added annotation while working on OT111-35
@Entity //IMPORTANT - ADDED @Entity annotation (missing in original class) while working on OT111-35
public class UserEntity {

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

    private String photo;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private RoleEntity role; //IMPORTANT! Changed "Role" name to "RoleEntity" in field class name while working on OT111-35.

    private OffsetDateTime createdDate;

    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    private Boolean deleted = Boolean.FALSE;

}
