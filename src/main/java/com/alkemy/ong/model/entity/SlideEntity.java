package com.alkemy.ong.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Table(name = "slide")
@SQLDelete(sql = "UPDATE slides SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Entity
public class SlideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String imageUrl;

    @Column(name = "text") //Not specify if nullable
    private String text;

    @Column(name = "order") //Not specify if nullable
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    //TODO: Create the organization entity to relate the tables (Foreign Key)
    private OrganizationEntity organizationId;
    
    //Auditory fields
    @Column(name = "created_date", nullable = false)
    private Timestamp created_date;
    @Column(name = "created_by", nullable = false)
    private String created_by;
    @Column(name = "modify_date")
    private Timestamp modify_date;
    @Column(name = "modify_by")
    private String modify_by;

    //Soft delete
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;
}
