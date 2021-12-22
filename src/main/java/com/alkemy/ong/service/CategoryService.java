package com.alkemy.ong.service;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO findCategoryById(Long id);

    CategoryResponseDTO saveCategory(CategoryRequestDTO request);

    void updateCategory(CategoryRequestDTO request, Long id);

    void deleteCategory(Long id);

    List<String> getCategories();

    CustomPage<CategoryResponseDTO> getCategoriesPageable(Pageable pageable);
}
