package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //IMPORTANT - Added @Repository annotation while working on OT111-35
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String roleName);
}