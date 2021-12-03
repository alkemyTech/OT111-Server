package com.alkemy.ong.service;

import com.alkemy.ong.model.dto.CategoryDTO;
import com.alkemy.ong.model.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    public List<CategoryDTO> getCategories();

}
