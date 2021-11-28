package com.alkemy.ong.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "member")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MemberEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        private String name;

        private String facebookUrl;

        private String instagramUrl;

        private String linkedinUrl;

        @NotNull
        private String image;

        private String description;

        @NotNull
        private OffsetDateTime createdDate;

        private OffsetDateTime modifiedDate;

        private String createdBy;

        private String modifiedBy;

        @NotNull
        private boolean deleted = Boolean.FALSE;
    }



