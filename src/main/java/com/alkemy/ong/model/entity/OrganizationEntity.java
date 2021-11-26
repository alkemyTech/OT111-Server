package com.alkemy.ong.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "organization")
@Getter
@Setter
@SQLDelete(sql = "UPDATE organization SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "image", nullable = false)
    private String image;

    @Column (name = "address")
    private String address;

    @Column (name = "phone")
    private Integer phone;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "welcomeText", nullable = false)
    private String welcomeText;

    @Column (name = "aboutUsText", nullable = false)
    private String aboutUsText;

//    Timestamps
    @CreationTimestamp
    @Column (name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column (name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @Column (name = "modified_date")
    private Timestamp modifiedDate;

    @Column (name = "modified_by")
    private String modifiedBy;

//    Soft Delete
    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;
}
