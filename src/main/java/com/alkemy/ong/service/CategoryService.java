package com.alkemy.ong.service;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO findCategory(Long id);

    CategoryResponseDTO saveCategory(CategoryRequestDTO request);

    boolean deleteCategory(Long id);

    CategoryResponseDTO updateCategory(CategoryRequestDTO request, Long id) throws Exception;

    List<CategoryDTO> getCategories();


}
