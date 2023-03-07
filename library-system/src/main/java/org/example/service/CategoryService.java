package org.example.service;

import org.example.dto.CategoryDto;
import org.example.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
    Category updateCategory(int categoryId, Category categoryDetails);
    void deleteCategoryById(int categoryId);
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories();
}
