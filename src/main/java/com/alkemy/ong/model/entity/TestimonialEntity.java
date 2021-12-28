package com.alkemy.ong.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
@Table(name = "testimonial")
@Getter
@Setter
@SQLDelete(sql = "UPDATE testimonial SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class TestimonialEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String image;

    private String content;

} 
