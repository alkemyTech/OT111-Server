package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Role findByName(String roleName);
}