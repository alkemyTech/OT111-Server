package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Integer> {
    UserEntity findByEmail(String email);
}
