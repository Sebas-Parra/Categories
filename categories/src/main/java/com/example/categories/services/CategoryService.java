package com.example.categories.services;

import com.example.categories.models.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
