package com.alkemy.ong.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "organization")
@Getter
@Setter
@SQLDelete(sql = "UPDATE organization SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private String address;

    private Integer phone;

    private String email;

    private String welcomeText;

    private String aboutUsText;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    //    Timestamps
    @Builder.Default
    private OffsetDateTime createdDate = OffsetDateTime.now();

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    //    Soft Delete
    private boolean deleted = Boolean.FALSE;

}
