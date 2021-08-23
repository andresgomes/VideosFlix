package br.com.videos.controller.dto;

import br.com.videos.modelo.Category;
import br.com.videos.modelo.CategoryColors;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    private String title;
    private Enum<CategoryColors> color;

    public CategoryDto(Category category) {
        this.title = category.getTitle();
        this.color = category.getColor();
    }

    public static List<CategoryDto> converter(List<Category> categories) {
        return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Enum<CategoryColors> getColor() {
        return color;
    }

    public void setColor(Enum<CategoryColors> color) {
        this.color = color;
    }
}
