package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO findCategory(Long id) {
        Optional<CategoryEntity> foundCategory = categoryRepository.findById(id);
        if(foundCategory.isPresent()){
            return categoryMapper.categoryEntity2DTO(foundCategory.get());
        }
        return null;
    }

    public CategoryResponseDTO saveCategory(CategoryRequestDTO request) {
        CategoryEntity newCategory = categoryMapper.categoryDTO2Entity(request);
        CategoryEntity savedCategory = categoryRepository.save(newCategory);
        return  categoryMapper.categoryEntity2DTO(savedCategory);
    }


    @Override
    @Transactional
    public boolean deleteCategory(Long id){
        try {
            Optional<CategoryEntity>categoryEntityOptional = categoryRepository.findById(id);
            CategoryEntity category = categoryEntityOptional.get();
            category.setDeleted(true);
            categoryRepository.save(category);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryRequestDTO request, Long id) throws Exception {

        Optional<CategoryEntity>categoryEntityOptional = categoryRepository.findById(id);
        if (!categoryEntityOptional.isPresent()){
            throw  new Exception("la categoria no existe");
        }
        CategoryEntity foundCategory = categoryEntityOptional.get();
        foundCategory.setName(request.getName());
        foundCategory.setImage(request.getImage());
        foundCategory.setDescription(request.getDescription());
        CategoryEntity updatedCategory = categoryRepository.save(foundCategory);


        return categoryMapper.categoryEntity2DTO(updatedCategory);
    }
}
