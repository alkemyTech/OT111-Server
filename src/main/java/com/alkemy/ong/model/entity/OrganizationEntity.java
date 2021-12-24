package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "organization")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE organization SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class OrganizationEntity extends AuditableEntity {

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

}
