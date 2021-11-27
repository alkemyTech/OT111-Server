package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
