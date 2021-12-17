package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);
}
