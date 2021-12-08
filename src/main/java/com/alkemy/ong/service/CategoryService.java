package com.alkemy.ong.service;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO findCategory(Long id);

    CategoryResponseDTO saveCategory(CategoryRequestDTO request);

    List<String> getCategories();

}
