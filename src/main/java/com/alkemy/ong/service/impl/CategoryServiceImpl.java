package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.EmailException;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO findCategoryById(Long id) {
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();
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
    public void deleteCategory(Long id) throws Exception {
        //TODO: CHEQUEAR A FUTURO
        categoryRepository.findById(id).orElseThrow();
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryRequestDTO request, Long id) throws Exception {
        CategoryEntity foundCategory = categoryRepository.findById(id).orElseThrow();
        foundCategory.setName(request.getName());
        foundCategory.setImage(request.getImage());
        foundCategory.setDescription(request.getDescription());
        CategoryEntity updatedCategory = categoryRepository.save(foundCategory);
        return categoryMapper.categoryEntity2DTO(updatedCategory);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<CategoryEntity> categoriesList = categoryRepository.findAll();
        return categoriesList.stream()
                .map(c -> new CategoryDTO(c.getName()))
                .collect(Collectors.toList());
    }

}
