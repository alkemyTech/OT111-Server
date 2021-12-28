package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.SlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<SlideEntity, Long> {

    List<SlideEntity> findByOrganizationId(Long id);
}
