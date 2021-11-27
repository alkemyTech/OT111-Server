package com.alkemy.ong.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MemberEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String facebookUrl;

        private String instagramUrl;

        private String linkedinUrl;

        private String image;

        private String description;

        private OffsetDateTime createdDate;

        private OffsetDateTime modifiedDate;

        private String createdBy;

        private String modifiedBy;

        private boolean deleted = Boolean.FALSE;
    }



