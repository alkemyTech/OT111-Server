package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Integer> {
    UserEntity findByEmail(String email);
=======

public interface UserRepository extends JpaRepository<UserEntity, Long> {
>>>>>>> d5864711cebb660ea145bd342e07f94380684526
}
