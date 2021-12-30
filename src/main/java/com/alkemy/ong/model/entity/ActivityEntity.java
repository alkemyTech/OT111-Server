package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "activity")
@SQLDelete(sql = "UPDATE activity SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity extends  AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    private String image;

}
