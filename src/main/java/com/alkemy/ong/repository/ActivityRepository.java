package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository <ActivityEntity, Long>{
}
