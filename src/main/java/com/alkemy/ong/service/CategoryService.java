package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.response.CategoryResponseDTO;

public interface CategoryService {

    CategoryResponseDTO findCategory(Long id);
}
