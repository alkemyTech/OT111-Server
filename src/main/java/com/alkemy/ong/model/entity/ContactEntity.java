package com.alkemy.ong.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String message;

    private boolean deleted;

}
