package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.TestimonialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<TestimonialEntity, Long> {

}
