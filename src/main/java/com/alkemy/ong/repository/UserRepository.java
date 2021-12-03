package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //IMPORTANT - Added @Repository annotation while working on OT111-35
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
