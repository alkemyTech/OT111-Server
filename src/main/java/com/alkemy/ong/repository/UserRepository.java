package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository <UserEntity, Long> {
=======
public interface UserRepository extends JpaRepository <UserEntity,Long> {
>>>>>>> e69f8e1f8d0766df6e81b886d724a3675cfcc946
    UserEntity findByEmail(String email);
}
