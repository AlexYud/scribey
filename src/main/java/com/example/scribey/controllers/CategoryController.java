package com.example.scribey.controllers;

import com.example.scribey.domain.category.Category;
import com.example.scribey.domain.category.CategoryResponseDTO;
import com.example.scribey.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        List<Category> categoryList = categoryService.findAll();
        return ResponseEntity.ok(formatListToSequence(categoryList));
    }

    private SequencedCollection<CategoryResponseDTO> formatListToSequence(List<Category> categoryList) {
        return categoryList.stream().map(category -> new CategoryResponseDTO(category.getId(), category.getTitle())).collect(Collectors.toList());
    }
}
