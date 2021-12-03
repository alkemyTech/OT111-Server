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
@Table(name = "activity")
@SQLDelete(sql = "UPDATE activity SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    private String image;

    //  Timestamps

    private OffsetDateTime createdDate;

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    //  SoftDelete
    private Boolean deleted = Boolean.FALSE;
}
