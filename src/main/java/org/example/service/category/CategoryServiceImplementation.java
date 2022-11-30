package org.example.service.category;

import org.example.entity.Category;
import org.example.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImplementation implements CategoryService{

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public void addCategory(Category category) {
        repository.addCategory(category);
    }

    @Override
    public boolean updateCategory(int categoryID, String imgPath) {
        int affectedRows = repository.updateCategory(categoryID, imgPath);
        return affectedRows == 1;
    }

    @Override
    public boolean removeCategory(int categoryID) {
        int affectedRows = repository.removeCategory(categoryID);
        return affectedRows == 1;
    }
}
