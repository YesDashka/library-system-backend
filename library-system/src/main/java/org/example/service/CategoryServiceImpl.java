package org.example.service;

import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = CategoryDto.convertToCategory(categoryDto);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int categoryId, Category categoryDetails) {
        //catch exception
        Category updatedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException());
        updatedCategory.setName(categoryDetails.getName());
        return updatedCategory;
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("not id"));
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
