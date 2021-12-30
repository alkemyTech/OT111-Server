package com.alkemy.ong.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private OrganizationEntity organization;


}
