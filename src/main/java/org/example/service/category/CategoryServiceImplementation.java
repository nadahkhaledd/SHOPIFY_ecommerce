package org.example.service.category;

import org.example.entity.Category;
import org.example.model.Response;
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
     * @return
     * @inheritDoc
     */
    @Override
    public Response addCategory(Category category) {

        category.setName(category.getName().toLowerCase());
         return  repository.addCategory(category);

    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> updateCategory(Category category) {
        return repository.updateCategory(category);

    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeCategory(int categoryID) {
        return repository.removeCategory(categoryID);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Category>> getAllCategories() {
        return repository.getAllCategories();
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Category> getCategoryByName(String name) {
        return repository.getCategoryByName(name);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Category> getCategoryByID(int id) {
        return repository.getCategoryByID(id);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Category>> searchByCategoryName(String categoryName) {
        return repository.searchByCategoryName(categoryName);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<List<String>> getCategoriesNames() {
        return repository.getCategoriesNames();
    }
}
