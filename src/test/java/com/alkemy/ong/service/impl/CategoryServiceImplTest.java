package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.mapper.CategoryMapper;
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


    CategoryResponseDTO cat1Dto = new CategoryResponseDTO(1L, "Category 1", "Description 1", "Image 1");
    CategoryResponseDTO cat2Dto = new CategoryResponseDTO(2L, "Category 2", "Description 2", "Image 2");
    CategoryResponseDTO cat3Dto = new CategoryResponseDTO(3L, "Category 3", "Description 3", "Image 3");
    List<CategoryResponseDTO> myCategoriesDTO = new ArrayList<>(Arrays.asList(cat1Dto, cat2Dto, cat3Dto));

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @DisplayName("FindCategoryByID - Should Pass")
    @Test
    void findCategoryById_success() throws Exception {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(Mocks.newCategory()));
        CategoryResponseDTO foundCat = categoryServiceImpl.findCategoryById(1L);
        assertEquals(Mocks.newCategory().getName(), foundCat.getName());
        assertEquals(Mocks.newCategory().getDescription(), foundCat.getDescription());
        assertEquals(Mocks.newCategory().getImage(), foundCat.getImage());
    }

    @Test
    void saveCategory() {
    }

    @Test
    void deleteCategory() {
        // Verify que repo fue llamado.
    }

    @Test
    void updateCategory() {
    }

    @DisplayName("GetCategoires - Should Pass")
    @Test
    void getCategories_success() throws Exception {
        when(categoryRepository.findAll()).thenReturn(List.of(Mocks.newCategory()));
        List<CategoryResponseDTO> myList = categoryServiceImpl.getCategories();
        assertEquals(Mocks.newCategory().getName(), myList.get(0).getName());
    }
}