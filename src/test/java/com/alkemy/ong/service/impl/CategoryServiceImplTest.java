package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.entity.CategoryEntity;
import com.alkemy.ong.model.mapper.CategoryMapper;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryService categoryService;

    @Mock
    CategoryMapper categoryMapper;

    CategoryEntity cat1 = new CategoryEntity(1, "Category 1", "Description 1", "Image 1");
    CategoryEntity cat2 = new CategoryEntity(2, "Category 2", "Description 2", "Image 2");
    CategoryEntity cat3 = new CategoryEntity(3, "Category 3", "Description 3", "Image 3");
    List<CategoryEntity> myCategories = new ArrayList<>(Arrays.asList(cat1, cat2, cat3));

    CategoryResponseDTO cat1Dto = new CategoryResponseDTO(1L, "Category 1", "Description 1", "Image 1");
    CategoryResponseDTO cat2Dto = new CategoryResponseDTO(2L, "Category 2", "Description 2", "Image 2");
    CategoryResponseDTO cat3Dto = new CategoryResponseDTO(3L, "Category 3", "Description 3", "Image 3");
    List<CategoryResponseDTO> myCategoriesDTO = new ArrayList<>(Arrays.asList(cat1Dto, cat2Dto, cat3Dto));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findCategoryById_success() throws Exception {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(cat1));
        when(categoryMapper.categoryEntity2DTO(any(CategoryEntity.class))).thenReturn(cat1Dto);
        CategoryResponseDTO foundCat = categoryServiceImpl.findCategoryById(1L);
        assertEquals("Category 1", foundCat.getName());
        assertEquals("Description 1", foundCat.getDescription());
    }

    @Test
    void saveCategory() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void updateCategory() {
    }

    @DisplayName("GetCategoires - Should Pass")
    @Test
    void getCategories_success() throws Exception {
        when(categoryRepository.findAll()).thenReturn(myCategories);
        when(categoryMapper.buildToList(any(CategoryEntity.class))).thenCallRealMethod();
        List<CategoryResponseDTO> myList = categoryServiceImpl.getCategories();
        assertEquals(2, myList.size());
    }
}