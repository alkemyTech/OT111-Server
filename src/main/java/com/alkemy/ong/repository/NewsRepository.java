package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.NewsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long>{
//Custom methods.
}
