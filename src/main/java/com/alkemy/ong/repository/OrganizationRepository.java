package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

    Optional<OrganizationEntity> findTopByOrderByIdDesc();
}
