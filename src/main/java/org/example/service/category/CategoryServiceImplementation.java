package org.example.service.category;

import org.example.entity.Category;
import org.example.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService{
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository repository) {
        this.repository = repository;
    }


    /**
     * @inheritDoc
     */
    @Override
    public void addCategory(Category category) {

        category.setName(category.getName().toLowerCase());
        repository.addCategory(category);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean updateCategory(Category category) {
        int affectedRows = repository.updateCategory(category);
        return affectedRows == 1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean removeCategory(int categoryID) {
        int affectedRows = repository.removeCategory(categoryID);
        return affectedRows == 1;
    }

    /**
     * @InheritedDoc
     */
    @Override
    public List<Category> getAllCategories() {
        return repository.getAllCategories();
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Category getCategoryByName(String name) {
        return repository.getCategoryByName(name);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Category getCategoryByID(int id) {
        return repository.getCategoryByID(id);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public List<Category> searchByCategoryName(String categoryName) {
        return repository.searchByCategoryName(categoryName);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getCategoriesNames() {
        return repository.getCategoriesNames();
    }
}
