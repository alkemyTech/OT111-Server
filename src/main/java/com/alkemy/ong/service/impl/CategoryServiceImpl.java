package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.ApiExceptionHandler;
import com.alkemy.ong.exception.ApiRequestException;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO findCategory(Long id) {
        CategoryEntity foundCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new ApiRequestException("Categoria con ID " + id + " no encontrada."));
        return categoryMapper.categoryEntity2DTO(foundCategory);
    }

    public CategoryResponseDTO saveCategory(CategoryRequestDTO request) {
        CategoryEntity newCategory = categoryMapper.categoryDTO2Entity(request);
        CategoryEntity savedCategory = categoryRepository.save(newCategory);
        CategoryResponseDTO result = categoryMapper.categoryEntity2DTO(savedCategory);
        return result;
    }

}
