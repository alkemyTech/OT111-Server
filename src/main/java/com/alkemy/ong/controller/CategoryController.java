package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    private ResponseEntity<CategoryResponseDTO> getCategoryDetails(@PathVariable Long id) {
        CategoryResponseDTO categoryDetails = categoryService.findCategory(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryDetails);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createNewCategory(@RequestBody CategoryRequestDTO request){
        CategoryResponseDTO response = categoryService.saveCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
