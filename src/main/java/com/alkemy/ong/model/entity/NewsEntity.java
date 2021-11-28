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
@Table(name = "news") //Both singular and plural of "news" word it's the same.
@Entity
@SQLDelete(sql = "UPDATE news SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    private String image;

    @NotNull //Confirm annotation
    private Long categoryId;

    @NotNull
    private OffsetDateTime createdDate;

    private OffsetDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;

    @NotNull
    private boolean deleted = Boolean.FALSE;

}
