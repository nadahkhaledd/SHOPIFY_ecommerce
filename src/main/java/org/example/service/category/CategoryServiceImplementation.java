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
     * @InheritedDoc
     */
    @Override
    public Response addCategory(Category category) {
        if(category == null)
            throw new NullPointerException();
        category.setName(category.getName().toLowerCase());
        if(repository.getCategoryByName(category.getName()).getObjectToBeReturned()!=null)
            return new Response<>("category already exists", 400, true, true);

         return  repository.addCategory(category);

    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Boolean> updateCategory(Category category) {
        if(category == null)
            throw new NullPointerException();
//        if(repository.getCategoryByID(category.getId()).getObjectToBeReturned()==null) {
//            repository.addCategory(category);
//            return new Response<>("Done", 200, false, false, true);
//        }
        return repository.updateCategory(category);

    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Boolean> removeCategory(Category category) {
        if(category == null)
            throw new NullPointerException();
        return repository.removeCategory(category);
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
        if(name.isBlank())
            throw new IllegalArgumentException();
        if(name.equals(null))
            throw new NullPointerException();
        return repository.getCategoryByName(name);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Category> getCategoryByID(int id) {
        if(id == -1)
            throw new IllegalArgumentException();
        return repository.getCategoryByID(id);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Category>> searchByCategoryName(String categoryName) {
        if(categoryName.isBlank())
            throw new IllegalArgumentException();
        if(categoryName.equals(null))
            throw new NullPointerException();
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
