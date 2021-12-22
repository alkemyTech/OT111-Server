package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO findCategoryById(Long id) {
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();
        return categoryMapper.toDTO(foundCategory);
    }

    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO request) {
        CategoryEntity newCategory = categoryMapper.toEntity(request);
        CategoryEntity savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        var foundCategory = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(foundCategory);
    }

    @Override
    public void updateCategory(CategoryRequestDTO request, Long id) {
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();
        foundCategory.setName(request.getName());
        foundCategory.setDescription(request.getDescription());
        foundCategory.setImage(request.getImage());
        categoryRepository.save(foundCategory);
    }

    @Override
    public List<String> getCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public CustomPage<CategoryResponseDTO> getCategoriesPageable(Pageable pageRequest) {
        return new CustomPage<>(categoryRepository.findAll(pageRequest)
                .map(categoryMapper::toDTO));
    }

}
