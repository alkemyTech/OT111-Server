package com.alkemy.ong.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor//Se agrego en tarea de 45
@NoArgsConstructor//Se agrego en tarea de 45
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

    private boolean deleted;
}
