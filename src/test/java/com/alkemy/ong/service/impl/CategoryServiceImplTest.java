//package com.alkemy.ong.service.impl;
//
//import com.alkemy.ong.model.entity.CategoryEntity;
//import com.alkemy.ong.model.mapper.CategoryMapper;
//import com.alkemy.ong.model.request.CategoryRequestDTO;
//import com.alkemy.ong.model.response.CategoryResponseDTO;
//import com.alkemy.ong.repository.CategoryRepository;
//import com.alkemy.ong.service.CategoryService;
//import com.alkemy.ong.utils.Mocks;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CategoryServiceImplTest {
//
//    @InjectMocks
//    CategoryServiceImpl categoryServiceImpl;
//
//    @Mock
//    CategoryRepository categoryRepository;
//
//    @Mock
//    CategoryService categoryService;
//
//    @Spy
//    CategoryMapper categoryMapper = new CategoryMapper();
//
//    @Test
//    void findCategoryById_shouldMapEntityToDTO() throws Exception {
//        when(categoryRepository.findById(9999L)).thenReturn(Optional.of(Mocks.newCategory()));
//        CategoryResponseDTO foundCat = categoryServiceImpl.findCategoryById(9999L);
//        assertEquals(Mocks.newCategory().getName(), foundCat.getName());
//        assertEquals(Mocks.newCategory().getDescription(), foundCat.getDescription());
//        assertEquals(Mocks.newCategory().getImage(), foundCat.getImage());
//    }
//
//    @Test
//    void saveCategory() {
//        CategoryEntity toBeSaved = categoryMapper.categoryDTO2Entity(Mocks.newCategoryRequestDTO());
//        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(toBeSaved);
//        CategoryResponseDTO savedCategory = categoryServiceImpl.saveCategory(Mocks.newCategoryRequestDTO());
//        // TODO: Deberia ser assertNotEquals() y when(save).thenReturn(Mocks.newCategory())???
//        assertEquals(Mocks.newCategoryRequestDTO().getName(), savedCategory.getName());
//        assertEquals(Mocks.newCategoryRequestDTO().getDescription(), savedCategory.getDescription());
//        assertEquals(Mocks.newCategoryRequestDTO().getImage(), savedCategory.getImage());
//    }
//
//    @Test
//    void deleteCategory() {
//        CategoryEntity toDelete = Mocks.newCategory();
//        when(categoryRepository.findById(9999L)).thenReturn(Optional.of(toDelete));
//        categoryServiceImpl.deleteCategory(9999L);
//        verify(categoryRepository, times(1)).delete(toDelete);
//    }
//
//    @Test
//    void updateCategory() {
//        when(categoryRepository.findById(9999L)).thenReturn(Optional.of(Mocks.newCategory()));
//        categoryServiceImpl.updateCategory(Mocks.newCategoryRequestDTO(), 9999L);
//        // TODO: Como verificar que los Setter fueron usado? foundCategory.setName(request.getName());
//        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
//    }
//
//    @Test
//    void getCategories_shouldMapEntityListToDTOList() throws Exception {
//        when(categoryRepository.findAll()).thenReturn(List.of(Mocks.newCategory()));
//        List<CategoryResponseDTO> myList = categoryServiceImpl.getCategories();
//        assertEquals(Mocks.newCategory().getName(), myList.get(0).getName());
//    }
//}