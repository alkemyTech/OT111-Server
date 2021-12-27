package com.alkemy.ong.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Getter
@Setter
@Table(name = "slide")
@SQLDelete(sql = "UPDATE slide SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Entity
public class SlideEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String text;

    @Column(name = "order_number")
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    //TODO: Create the organization entity to relate the tables (Foreign Key)
    private OrganizationEntity organization;
    

}
