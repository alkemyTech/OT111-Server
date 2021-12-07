package com.alkemy.ong.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
@SQLDelete(sql = "UPDATE category SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    private String description;

    private String image;

    private OffsetDateTime createdDate = OffsetDateTime.now();

    private OffsetDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private boolean deleted = Boolean.FALSE;

}
