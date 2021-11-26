package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Integer> {

}
