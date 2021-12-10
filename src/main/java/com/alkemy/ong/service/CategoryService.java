package com.alkemy.ong.service;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO findCategoryById(Long id);

    CategoryResponseDTO saveCategory(CategoryRequestDTO request);

    CategoryResponseDTO updateCategory(CategoryRequestDTO request, Long id) throws Exception;

    void deleteCategory(Long id) throws Exception;

    List<CategoryDTO> getCategories();

}
