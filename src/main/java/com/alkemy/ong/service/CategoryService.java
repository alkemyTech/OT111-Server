package com.alkemy.ong.service;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;

public interface CategoryService {

    CategoryResponseDTO findCategory(Long id);

    CategoryResponseDTO saveCategory(CategoryRequestDTO request);

}
