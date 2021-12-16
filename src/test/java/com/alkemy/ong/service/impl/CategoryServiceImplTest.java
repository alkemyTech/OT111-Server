package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.utils.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryService categoryService;

    @Spy
    CategoryMapper categoryMapper = new CategoryMapper();

    @Test
    void findCategoryById_shouldMapEntityToDTO() throws Exception {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(Mocks.newCategory()));
        CategoryResponseDTO foundCat = categoryServiceImpl.findCategoryById(1L);
        assertEquals(Mocks.newCategory().getName(), foundCat.getName());
        assertEquals(Mocks.newCategory().getDescription(), foundCat.getDescription());
        assertEquals(Mocks.newCategory().getImage(), foundCat.getImage());
    }

    @Test
    void saveCategory() {
        CategoryRequestDTO catDTO = new CategoryRequestDTO();
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(Mocks.newCategory());
        CategoryResponseDTO savedCategory = categoryServiceImpl.saveCategory(catDTO);

        // TODO: Como verifico que mappea mi DTO a Entidad, antes del SAVE?

        assertEquals(Mocks.newCategory().getName(), savedCategory.getName());
        assertEquals(Mocks.newCategory().getDescription(), savedCategory.getDescription());
        assertEquals(Mocks.newCategory().getImage(), savedCategory.getImage());
    }

    @Test
    void deleteCategory() {
        // Verify que repo fue llamado.
    }

    @Test
    void updateCategory() {
        CategoryRequestDTO catDTO = new CategoryRequestDTO();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(Mocks.newCategory()));
        categoryServiceImpl.updateCategory(catDTO, 1L);

        // TODO: Como pasar verificar que el save es distinto al find? En caso de haber realizado un cambio


    }

    @Test
    void getCategories_shouldMapEntityListToDTOList() throws Exception {
        when(categoryRepository.findAll()).thenReturn(List.of(Mocks.newCategory()));
        List<CategoryResponseDTO> myList = categoryServiceImpl.getCategories();
        assertEquals(Mocks.newCategory().getName(), myList.get(0).getName());
    }
}