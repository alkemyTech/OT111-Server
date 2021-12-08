package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Collection;


@Data
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
//    @NotNull
//    private String password; //TODO Enable with the other passwords tasks when completed

    @NotNull
    private String photo;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role; //IMPORTANT! Changed "Role" name to "RoleEntity" in field class name while working on OT111-35.

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id")
    )
    private Collection<RoleEntity> roles;

    @Builder.Default
    private OffsetDateTime createdDate = OffsetDateTime.now();

    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    private Boolean deleted = Boolean.FALSE;

}
