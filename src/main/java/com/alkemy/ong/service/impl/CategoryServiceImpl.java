package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO findCategoryById(Long id) {
        System.out.println(id);
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();
        System.out.println(foundCategory);
        return categoryMapper.categoryEntity2DTO(foundCategory);
    }

    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO request) {
        CategoryEntity newCategory = categoryMapper.categoryDTO2Entity(request);
        CategoryEntity savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.categoryEntity2DTO(savedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        var foundCategory = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(foundCategory);
    }

    @Override
    public void updateCategory(CategoryRequestDTO request, Long id) {
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();
        foundCategory.setName(request.getName());
        foundCategory.setImage(request.getImage());
        foundCategory.setDescription(request.getDescription());
        categoryRepository.save(foundCategory);
    }

    @Override
    public List<CategoryResponseDTO> getCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::buildToList)
                .collect(Collectors.toList());
    }

}
