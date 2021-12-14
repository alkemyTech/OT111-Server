package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //IMPORTANT - Added @Repository annotation in branch OT111-35
public interface ActivityRepository extends JpaRepository <ActivityEntity, Long>{
}
