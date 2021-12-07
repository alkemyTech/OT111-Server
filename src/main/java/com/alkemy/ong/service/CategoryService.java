package com.alkemy.ong.service;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;

public interface CategoryService {

    CategoryResponseDTO saveCategory(CategoryRequestDTO request);

}
