package org.example.dto;

import org.example.entity.Category;

public class CategoryDto {
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Category convertToCategory(CategoryDto categoryDto) {
        return Category.builder().
                name(categoryDto.name)
                .build();
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
