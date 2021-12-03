package com.alkemy.ong.service;

import com.alkemy.ong.model.dto.CategoryDTO;
import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.utils.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getCategories() {

        List<CategoryDTO> dto = new ArrayList<>();

        Iterable<CategoryEntity> categories = this.categoryRepository.findAll();

        for(CategoryEntity category : categories){

            CategoryDTO categoryDTO = MHelpers.modelMapper().map(category, CategoryDTO.class);
            dto.add(categoryDTO);
        }
        return dto;
    }
}
